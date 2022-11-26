package br.com.etechoracio.pw2BdSupermercado.entity;

import javax.persistence.*;

import br.com.etechoracio.pw2BdSupermercado.Formatador;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Cliente")
public class Cliente implements IListavel {	
	@Id
	@Column(name = "cod_clie", columnDefinition = "char(5)")
	private String codigo;
	
	@Column(name = "cpf_clie", columnDefinition = "char(11)")
	private String cpf;

	public void setCodigo(String codigo) {
		if (codigo.length() != 5)
			throw new IllegalArgumentException();
		this.codigo = codigo;
	}

	public void setCpf(String cpf) {
		if (cpf.length() != 11)
			throw new IllegalArgumentException();
		this.cpf = cpf;
	}

	public void listar() {
		Formatador f = new Formatador();
		System.out.println(String.format("Cliente %s: %s", codigo, f.cpf(cpf)));
	}

	public void cadastrar(String codigo, String cpf) {
		this.setCodigo(codigo);
		this.setCpf(cpf);
	}
}
