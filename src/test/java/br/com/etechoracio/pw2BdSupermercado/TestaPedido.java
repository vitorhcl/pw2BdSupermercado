package br.com.etechoracio.pw2BdSupermercado;

import java.util.Set;

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
		
		Atendente atendente = TestaAtendente.criaAtendente();

		FormPag formpag = FormPag.builder().codigo(FormPagEnum.CREDITO).nome("Crédito").build();

		Nf nf = Nf.builder().codBar("7899586528937").numero(2535).build();

		Pedido pedido = Pedido.builder().itens(Set.of(item1, item2)).numero("000001").cliente(cliente).formPag(formpag).nf(nf).atendente(atendente).build();
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
