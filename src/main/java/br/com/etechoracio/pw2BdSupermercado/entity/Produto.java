package br.com.etechoracio.pw2BdSupermercado.entity;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import br.com.etechoracio.pw2BdSupermercado.Formatador;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@Entity
@Table(name = "Produto")
public class Produto implements IListavel {
	@Id
	@Column(name = "cod_bar")
	private String codBar;
	
	@Column(name = "nome_prod")
	private String nome;
	
	@Column(name = "preco_venda")
	private double precoVenda;
	
	@Column(name = "preco_custo")
	private double precoCusto;
	
	@Column(name = "estoque")
	private int estoque = 0;
	
	@Column(name = "categoria")
	private String categoria;
	
	private List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
	
	@OneToMany(mappedBy = "itemPedPk.produto",
			   cascade = CascadeType.ALL)
	private List<ItemPed> itens = new ArrayList<ItemPed>();


	public String getCodBar() {
		return codBar;
	}

	public void setCodBar(String codBar) {
		if (codBar.length() != 13)
			throw new IllegalArgumentException();
		this.codBar = codBar;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public double getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(double precoCusto) {
		this.precoCusto = precoCusto;
	}

	public int getEstoque() {
		return estoque;
	}

	public void adicionar(int quantidade) {
		this.estoque += quantidade;
	}

	public void remover(int quantidade) {
		this.estoque -= quantidade;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public void listar() {
		Formatador f = new Formatador();

		System.out.println("Nome: " + this.getNome());
		System.out.println("Código de barras: " + f.codBar(this.getCodBar()));
		System.out.println("Preço de custo: " + f.moeda(this.getPrecoCusto()));
		System.out.println("Preço de venda: " + f.moeda(this.getPrecoVenda()));
		System.out.println("Categoria: " + this.getCategoria());
		System.out.println("Estoque: " + this.getEstoque());
		
		System.out.print("Fornecedores: ");
		this.fornecedores.forEach(forn -> System.out.print(forn.getNome() + "; "));
		System.out.println();
	}

	public void cadastrar(String nome, String codBar, String categoria, double precoCusto, double precoVenda) {
		this.nome = nome;
		this.codBar = codBar;
		this.categoria = categoria;
		this.precoVenda = precoVenda;
		this.categoria = categoria;
	}
}
