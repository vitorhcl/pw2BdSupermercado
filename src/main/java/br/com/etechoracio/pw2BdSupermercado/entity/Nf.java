package br.com.etechoracio.pw2BdSupermercado.entity;
import java.time.LocalDateTime;

import javax.persistence.*;

import br.com.etechoracio.pw2BdSupermercado.Formatador;
import br.com.etechoracio.pw2BdSupermercado.enums.FormPagEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "nf")
public class Nf implements IListavel {
	@Id
	@Column(name = "num_nf", columnDefinition = "numeric(4)")
	public int numero;
	
	@Column(name = "total_nf", columnDefinition = "numeric(10,2)")
	public double total;
	
	@Column(name = "qtd_total", columnDefinition = "numeric(3)")
	private int qtdTotal;

	@Column(name = "cod_bar", columnDefinition = "char(13)")
	private String codBar;
	
	@OneToOne
	@JoinColumn(name = "num_pedido", columnDefinition = "numeric(6)")
	private Pedido pedido;
	
	@Builder.Default
	@Column(name = "data_emi")
	private LocalDateTime dataEmi = LocalDateTime.now();

	public void setCodBar(String codBar) {
		if (codBar.length() != 13)
			throw new IllegalArgumentException();
		this.codBar = codBar;
	}

	public void listar() {
		Formatador f = new Formatador();

		System.out.println("Número: " + this.getNumero());
		System.out.println("Número do pedido: " + this.getPedido().getNumero());
		System.out.println("Código de barras: " + f.codBar(this.getCodBar()));
		System.out.println("Total de itens: " + this.getQtdTotal());
		System.out.println("Valor total: " + f.moeda(this.getTotal()));
		System.out.println("Data de emissão: " + f.data(this.getDataEmi()));
	}
}
