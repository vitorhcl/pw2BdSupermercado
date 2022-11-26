package br.com.etechoracio.pw2BdSupermercado.entity;
import java.time.LocalDateTime;

import javax.persistence.*;

import br.com.etechoracio.pw2BdSupermercado.Formatador;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@Table(name = "nf")
public class Nf implements Listavel {
	@Id
	@Column(name = "num_nf")
	public String num;
	
	@Column(name = "total_nf")
	public int total;
	
	@Column(name = "qtd_total")
	private int qtdTotal;
	
	@OneToOne
	@JoinColumn(name = "cod_bar")
	private String codBar;
	
	@OneToOne
	@JoinColumn(name = "num_pedido")
	private Pedido pedido;
	
	@Builder.Default
	@Column(name = "data_emi")
	private LocalDateTime dataEmi = LocalDateTime.now();

	public void setNum(String num) {
		if (num.length() != 4)
			throw new IllegalArgumentException();
		this.num = num;
	}

	public void setCodBar(String codBar) {
		if (codBar.length() != 13)
			throw new IllegalArgumentException();
		this.codBar = codBar;
	}

	public void listar() {
		Formatador f = new Formatador();

		System.out.println("Número: " + this.getNum());
		System.out.println("Número do pedido: " + this.getPedido().getNumero());
		System.out.println("Código de barras: " + f.codBar(this.getCodBar()));
		System.out.println("Total de itens: " + this.getQtdTotal());
		System.out.println("Valor total: " + f.moeda(this.getTotal()));
		System.out.println("Data de emissão: " + f.data(this.getDataEmi()));
	}
}
