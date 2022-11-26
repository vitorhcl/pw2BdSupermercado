package br.com.etechoracio.pw2BdSupermercado.entity;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Pedido")
public class Pedido implements IListavel {
	@Id
	@Column(name = "num_pedido")
	private String numero;
	
	@ManyToOne
	@JoinColumn(name = "cod_clie")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "cod_pag")
	private FormPag formPag;
	
	@ManyToOne
	@JoinColumn(name = "cpf_func")
	private Atendente atendente;

	@OneToOne
	@JoinColumn(name = "num_nf", columnDefinition = "numeric(4)")
	private Nf nf;
	
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public FormPag getFormPag() {
		return formPag;
	}

	public void setFormPag(FormPag formPag) {
		this.formPag = formPag;
	}

	public Atendente getAtendente() {
		return atendente;
	}

	public void setAtendente(Atendente atendente) {
		this.atendente = atendente;
	}

	public void atendido() {
		if (this.atendido)
			System.out.println("Este pedido já foi atendido!");
		this.atendido = true;
	}

	public List<ItemPed> getItens() {
		return itens;
	}

	public void setItens(List<ItemPed> itens) {
		this.itens = itens;
	}

	public Nf getNf() {
		return this.nf;
	}

	public void setNf(Nf nf) {
		this.nf = nf;
	}

	public void cadastrar(String numero, Cliente cliente, FormPag formPag, Nf nf) {
		this.setNumero(numero);
		this.cliente = cliente;
		this.formPag = formPag;

		nf.setPedido(this);
		int qtdTotal = 0;
		double vlrTotal = 0;
		for (ItemPed item : this.itens) {
			qtdTotal += item.getQtd();
			vlrTotal += item.getProduto().getPrecoVenda() * item.getQtd();
		}
		nf.setQtdTotal(qtdTotal);
		nf.setTotal(vlrTotal);
		this.nf = nf;
	}

	public void listar() {
		System.out.println("Número: " + this.getNumero());
		System.out.println("Forma de pagamento: " + this.getFormPag().getCodigo().toString());
		this.getCliente().listar();
		this.getItens().forEach(itemPed -> itemPed.listar());
		System.out.println("Foi atendido: " + (this.isAtendido() ? "sim" : "não"));
	}

	public void cadastrar(String numero, Cliente cliente, FormPag formPag, Nf nf, List<ItemPed> itens) {
		this.setNumero(numero);
		this.cliente = cliente;
		this.formPag = formPag;

		itens.forEach(itemPed -> itemPed.setPedido(this));
		this.itens = itens;

		nf.setPedido(this);
		int qtdTotal = 0;
		for (ItemPed item : itens) {
			qtdTotal += item.getQtd();
		}
		nf.setQtdTotal(qtdTotal);

		this.nf = nf;
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

	public boolean isAtendido() {
		return atendido;
	}
}
