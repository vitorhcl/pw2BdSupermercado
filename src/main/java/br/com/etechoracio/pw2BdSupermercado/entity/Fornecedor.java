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
@Table(name = "Fornecedor")
public class Fornecedor implements IListavel {
	@Id
	@Column(name = "cnpj_forn", columnDefinition = "char(14)")
	private String cnpj;
	
	@Column(name = "nome_forn")
	private String nome;
	
	@Builder.Default
	@ManyToMany
	@JoinTable(name = "Forn_prod",
			   joinColumns = @JoinColumn(name = "cnpj_forn", columnDefinition = "char(14)"),
			   inverseJoinColumns = @JoinColumn(name = "cod_bar", columnDefinition = "char(13"))
	private List<Produto> produtos = new ArrayList<Produto>();

	public void setCnpj(String cnpj) {
		if (cnpj.length() != 14)
			throw new IllegalArgumentException();
		this.cnpj = cnpj;
	}

	public void listar() {
		Formatador f = new Formatador();

		System.out.println("Nome: " + this.getNome());
		System.out.println("CNPJ: " + f.cnpj(this.getCnpj()));
		System.out.print("Produtos fornecidos: ");
		this.produtos.forEach(prod -> System.out.printf("%s (%s); ", prod.getNome(), prod.getCodBar()));
	}

	public void comprar(Produto prod, int quantidade) {
		prod.adicionar(quantidade);
		if(prod.getFornecedores().contains(this))
			return;
		prod.getFornecedores().add(this);
		this.getProdutos().add(prod);
	}

}
