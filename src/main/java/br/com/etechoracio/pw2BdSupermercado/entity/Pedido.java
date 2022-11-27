package br.com.etechoracio.pw2BdSupermercado.entity;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Pedido")
public class Pedido implements IListavel {
	@Id
	@Column(name = "num_pedido", columnDefinition = "numeric(6)")
	private String numero;
	
	@ManyToOne
	@JoinColumn(name = "cod_clie", columnDefinition = "char(5)")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "cod_pag", columnDefinition = "numeric(1)")
	private FormPag formPag;
	
	@ManyToOne
	@JoinColumn(name = "cpf_func", columnDefinition = "char(11)")
	private Atendente atendente;

	@OneToOne
	@JoinColumn(name = "num_nf", columnDefinition = "numeric(4)")
	private Nf nf;
	
	@Builder.Default
	@OneToMany(mappedBy = "itemPedPk.pedido",
			   fetch = FetchType.EAGER,
			   cascade = CascadeType.ALL)
	private Set<ItemPed> itens = new HashSet<ItemPed>();

	public void setNumero(String numero) {
		if (numero.length() != 6)
			throw new IllegalArgumentException();
		this.numero = numero;
	}

	public void listar() {
		System.out.println("Número: " + this.getNumero());
		System.out.println("Forma de pagamento: " + this.getFormPag().getCodigo().toString());
		this.getCliente().listar();
		this.getItens().forEach(itemPed -> itemPed.listar());
		
		System.out.printf("Atendido por: ");
		atendente.listar();
	}

	@Builder
	public static Pedido de(String numero, Cliente cliente, FormPag formPag, Nf nf, Atendente atendente, Set<ItemPed> itens) {
		Pedido pedido = new Pedido();
		pedido.setNumero(numero);
		pedido.cliente = cliente;
		pedido.formPag = formPag;
		pedido.atendente = atendente;

		itens.forEach(itemPed -> itemPed.setPedido(pedido));
		pedido.itens = itens;

		nf.setPedido(pedido);
		int qtdTotal = 0;
		double vlrTotal = 0;
		for (ItemPed item : pedido.itens) {
			qtdTotal += item.getQtd();
			vlrTotal += item.getProduto().getPrecoVenda() * item.getQtd();
		}
		nf.setQtdTotal(qtdTotal);
		nf.setTotal(vlrTotal);

		pedido.nf = nf;
		return pedido;
	}

	private void adicionarItem(ItemPed item) {
		item.getProduto().remover(item.getQtd());
		
		Produto prodExis;
		for (ItemPed itemExis : this.itens) {
			prodExis = itemExis.getProduto();
			if (item.getProduto().getCodBar() == prodExis.getCodBar()) {
				prodExis.adicionar(item.getQtd());
				return;
			}
		}

		this.itens.add(item);
	}

	public void adicionarItens(ItemPed... itens) {
		for (ItemPed item : itens)
			this.adicionarItem(item);
	}
}
