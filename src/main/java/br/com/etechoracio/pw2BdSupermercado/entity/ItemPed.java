package br.com.etechoracio.pw2BdSupermercado.entity;

import javax.persistence.*;

import br.com.etechoracio.pw2BdSupermercado.Formatador;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Ped_prod")
@AssociationOverrides({
	@AssociationOverride(name = "itemPedPk.produto",
		joinColumns = @JoinColumn(name = "cod_bar", columnDefinition = "char(13)")),
	@AssociationOverride(name = "itemPedPk.pedido",
		joinColumns = @JoinColumn(name = "num_pedido", columnDefinition = "numeric(4)")),
})
public class ItemPed implements IListavel {
	@EmbeddedId
	private ItemPedPk itemPedPk;

	@Column(name = "qtd_ped", columnDefinition = "numeric(3)")
	private int qtd;
	
	@Transient
	private Produto produto;
	
	@Transient
	private Pedido pedido;

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		if (qtd == 0)
			throw new IllegalArgumentException();
		this.qtd = qtd;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public void listar() {
		Formatador f = new Formatador();

		Produto produto = this.getProduto();
		System.out.println(String.format("%dx %s (%s): %s", this.getQtd(), produto.getNome(),
				f.codBar(produto.getCodBar()), f.moeda(produto.getPrecoVenda() * this.getQtd())));
	}

	public void cadastrar(Produto produto, int qtd) {
		this.produto = produto;
		this.qtd = qtd;
	}
}
