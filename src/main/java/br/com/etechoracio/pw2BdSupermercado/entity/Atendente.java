package br.com.etechoracio.pw2BdSupermercado.entity;

import javax.persistence.*;

import br.com.etechoracio.pw2BdSupermercado.Formatador;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "Atendente")
public class Atendente implements IListavel {
	@Id
	@Column(name = "cpf_func", columnDefinition = "char(11)")
	private String cpf;
	
	@Column(name = "salr_func", columnDefinition = "numeric(7,2)")
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
}
