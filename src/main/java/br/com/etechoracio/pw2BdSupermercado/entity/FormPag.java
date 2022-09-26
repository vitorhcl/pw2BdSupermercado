package br.com.etechoracio.pw2BdSupermercado.entity;

import javax.persistence.*;

import br.com.etechoracio.pw2BdSupermercado.enums.FormPagEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Form_pag")
public class FormPag implements Listavel {
	@Id
	@Column(name = "nome_pag")
	private String nome;
	private FormPagEnum forma;

	public void listar() {
		System.out.println(this.nome);
	}

	public FormPag(String nome, FormPagEnum forma) {
		this.nome = nome;
		this.forma = forma;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public FormPagEnum getForma() {
		return forma;
	}

	public void setForma(FormPagEnum forma) {
		this.forma = forma;
	}
}
