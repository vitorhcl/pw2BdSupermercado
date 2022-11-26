package br.com.etechoracio.pw2BdSupermercado.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class ItemPedPk implements Serializable {
	@ManyToOne(cascade = CascadeType.ALL)
	private Produto produto;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Pedido pedido;
}
