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
@Table(name = "Fornecedor")
public class Fornecedor implements Listavel {
	@Id
	@Column(name = "cnpj_forn")
	private String cnpj;
	
	@Column(name = "nome_forn")
	private String nome;
	private List<Produto> produtos = new ArrayList<Produto>();

	public void setCnpj(String cnpj) {
		if (cnpj.length() != 14)
			throw new IllegalArgumentException();
		this.cnpj = cnpj;
	}

	public void cadastrar(String nome, String cnpj) {
		this.nome = nome;
		this.setCnpj(cnpj);
	}

	public void listar() {
		Formatador f = new Formatador();

		System.out.println("Nome: " + this.nome);
		System.out.println("CNPJ: " + f.cnpj(this.getCnpj()));
		System.out.print("Produtos fornecidos: ");
		this.produtos.forEach(prod -> System.out.printf("%s (%s); ", prod.getNome(), prod.getCodBar()));
	}

	public void comprar(Produto prod, int quantidade) {
		prod.adicionar(quantidade);
		if(prod.getFornecedores().contains(this))
			return;
		prod.getFornecedores().add(this);
		this.produtos.add(prod);
	}

}
