package br.com.etechoracio.pw2BdSupermercado.entity;

import javax.persistence.*;

import br.com.etechoracio.pw2BdSupermercado.enums.FormPagEnum;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name="Form_pag")
public class FormPag implements IListavel {
	@Id
	@Enumerated(value = EnumType.ORDINAL)
	@Column(name = "cod_pag")
	private FormPagEnum codigo;

	@Column(name = "nome_pag")
	private String nome;

	public void listar() {
		System.out.println(this.nome);
	}
}