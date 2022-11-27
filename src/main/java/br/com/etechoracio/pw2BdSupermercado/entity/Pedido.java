package br.com.etechoracio.pw2BdSupermercado.entity;
import java.util.ArrayList;
import java.util.List;

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
	
	@Transient
	private boolean atendido = false;
	
	@OneToMany(mappedBy = "itemPedPk.pedido",
			   cascade = CascadeType.ALL)
	private List<ItemPed> itens = new ArrayList<ItemPed>();

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		if (numero.length() != 6)
			throw new IllegalArgumentException();
		this.numero = numero;
	}

	public void atendido() {
		if (this.atendido)
			System.out.println("Este pedido já foi atendido!");
		this.atendido = true;
	}

	public void listar() {
		System.out.println("Número: " + this.getNumero());
		System.out.println("Forma de pagamento: " + this.getFormPag().getCodigo().toString());
		this.getCliente().listar();
		this.getItens().forEach(itemPed -> itemPed.listar());
		System.out.println("Foi atendido: " + (this.isAtendido() ? "sim" : "não"));
	}

	@Builder
	public static Pedido de(String numero, Cliente cliente, FormPag formPag, Nf nf, Set<ItemPed> itens) {
		Pedido pedido = new Pedido();
		pedido.setNumero(numero);
		pedido.cliente = cliente;
		pedido.formPag = formPag;

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
