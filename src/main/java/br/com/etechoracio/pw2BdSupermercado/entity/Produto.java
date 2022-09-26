package br.com.etechoracio.pw2BdSupermercado.entity;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import br.com.etechoracio.pw2BdSupermercado.Formatador;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Produto")
public class Produto implements Listavel {
	private String codBar;
	private String nome;
	private double precoVenda;
	private double precoCusto;
	private int estoque = 0;
	private String categoria;
	private List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();

	public String getCodBar() {
		return codBar;
	}

	public void setCodBar(String codBar) {
		if (codBar.length() != 13)
			throw new IllegalArgumentException();
		this.codBar = codBar;
	}

	public void listar() {
		Formatador f = new Formatador();

		System.out.println("Nome: " + this.nome);
		System.out.println("Código de barras: " + f.codBar(this.getCodBar()));
		System.out.println("Preço de custo: " + f.moeda(this.precoCusto));
		System.out.println("Preço de venda: " + f.moeda(this.precoVenda));
		System.out.println("Categoria: " + this.categoria);
		System.out.println("Estoque: " + this.estoque);
		
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
