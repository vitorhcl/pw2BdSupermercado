package br.com.etechoracio.pw2BdSupermercado.entity;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import br.com.etechoracio.pw2BdSupermercado.Formatador;
import lombok.*;

@Getter
@Setter
@Builder
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
	
	@Builder.Default
	@Column(name = "estoque")
	private int estoque = 0;
	
	@Column(name = "categoria")
	private String categoria;
	
	@Builder.Default
	@ManyToMany
	@JoinTable(name = "Forn_prod",
			  joinColumns = @JoinColumn(name = "cod_bar", columnDefinition = "char(13)"),
			  inverseJoinColumns = @JoinColumn(name = "cnpj_forn", columnDefinition = "char(14)"))
	private List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
	
	@Builder.Default
	@OneToMany(mappedBy = "itemPedPk.produto",
			   cascade = CascadeType.ALL)
	private List<ItemPed> itens = new ArrayList<ItemPed>();

	public void setCodBar(String codBar) {
		if (codBar.length() != 13)
			throw new IllegalArgumentException();
		this.codBar = codBar;
	}

	public void adicionar(int quantidade) {
		this.estoque += quantidade;
	}

	public void remover(int quantidade) {
		this.estoque -= quantidade;
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
}
