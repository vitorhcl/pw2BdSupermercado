package br.com.etechoracio.pw2BdSupermercado.entity;
import java.time.LocalDate;

import javax.persistence.*;

import br.com.etechoracio.pw2BdSupermercado.Formatador;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	
	@Column(name = "data_emi")
	private LocalDate dataEmi;

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		if (num.length() != 4)
			throw new IllegalArgumentException();
		this.num = num;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getQtdTotal() {
		return qtdTotal;
	}

	public void setQtdTotal(int qtdTotal) {
		this.qtdTotal = qtdTotal;
	}

	public String getCodBar() {
		return codBar;
	}

	public void setCodBar(String codBar) {
		if (codBar.length() != 13)
			throw new IllegalArgumentException();
		this.codBar = codBar;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido numPedido) {
		this.pedido = numPedido;
	}

	public LocalDate getDataEmi() {
		return dataEmi;
	}

	public void setDataEmi(LocalDate dt) {
		this.dataEmi = dt;
	}

	public void cadastrar(String codBar, String num) {
		this.codBar = codBar;

		LocalDate dt = LocalDate.now();
		this.dataEmi = dt;

		this.num = num;
	}

	public void cadastrar(String codBar, String num, LocalDate dataEmi) {
		this.setCodBar(codBar);
		this.dataEmi = dataEmi;
		this.setNum(num);
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
