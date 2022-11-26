package br.com.etechoracio.pw2BdSupermercado.entity;

import javax.persistence.*;

import br.com.etechoracio.pw2BdSupermercado.enums.FormPagEnum;

public class FormPag implements IListavel {
	@Id
	@Column(name = "nome_pag")
	private int codigo;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "nome_pag")
	private FormPagEnum forma;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void listar() {
		System.out.println(this.forma.toString());
	}

	public FormPag(int codigo, FormPagEnum forma) {
		this.codigo = codigo;
		this.forma = forma;
	}

	public FormPagEnum getForma() {
		return forma;
	}

	public void setForma(FormPagEnum forma) {
		this.forma = forma;
	}
}
