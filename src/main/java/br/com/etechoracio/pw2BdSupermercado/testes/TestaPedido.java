package br.com.etechoracio.pw2BdSupermercado.testes;

import br.com.etechoracio.pw2BdSupermercado.Supermercado;
import br.com.etechoracio.pw2BdSupermercado.entity.*;
import br.com.etechoracio.pw2BdSupermercado.enums.FormPagEnum;

public class TestaPedido {
	public static Pedido criaPedido() {
		Produto produto1 = TestaProduto.criaProduto1();
		Produto produto2 = TestaProduto.criaProduto2();
		ItemPed item1 = new ItemPed();
		ItemPed item2 = new ItemPed();
		item1.cadastrar(produto1, 2);
		item2.cadastrar(produto2, 5);

		Cliente cliente = TestaCliente.criaCliente();

		FormPag formpag = new FormPag(1, FormPagEnum.CREDITO);

		Nf nf = new Nf();
		nf.cadastrar("7899586528937", "2535");

		Pedido pedido = new Pedido();
		pedido.adicionarItens(item1, item2);
		pedido.cadastrar("000001", cliente, formpag, nf);
		Supermercado.getPedidos().add(pedido);
		Supermercado.getNfs().add(nf);
		return pedido;
	}

	public static void main(String[] args) {
		Pedido pedido = TestaPedido.criaPedido();
		System.out.println("Pedido:");
		pedido.listar();

		System.out.println("\nForma de pagamento:");
		pedido.getFormPag().listar();

		System.out.println("\nNota Fiscal: ");
		pedido.getNf().listar();
	}
}
