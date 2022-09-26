package br.com.etechoracio.pw2BdSupermercado.entity;

import javax.persistence.*;

import br.com.etechoracio.pw2BdSupermercado.Formatador;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Atendente")
public class Atendente implements Listavel {
	@Id
	@Column(name = "cpf_func")
	private String cpf;
	
	@Column(name = "salr_func")
	private double slr;
	
	@Column(name = "nome_aten")
	private String nome;

	public void setCpf(String cpf) {
		if (cpf.length() != 11)
			throw new IllegalArgumentException();
		this.cpf = cpf;
	}

	public void listar() {
		Formatador f = new Formatador();
		System.out.println(String.format("%s (%s) ganha %s", this.nome, f.cpf(this.cpf), f.moeda(this.slr)));
	}

	public void cadastrar(String cpf, String nome, double slr) {
		this.nome = nome;
		this.setCpf(cpf);
		this.slr = slr;
	}

	public void atenderPedido(Pedido pedido) {
		pedido.setAtendente(this);
		pedido.atendido();
	}
}
