package br.com.etechoracio.pw2BdSupermercado.testes;

import br.com.etechoracio.pw2BdSupermercado.Supermercado;
import br.com.etechoracio.pw2BdSupermercado.entity.*;

public class TestaAtendente {
	public static Atendente criaAtendente() {
		Atendente aten = new Atendente();
		aten.cadastrar("13032350050", "Lúcia", 1800);
		Supermercado.getAtendentes().add(aten);
		return aten;
	}

	public static void main(String[] args) {
		Pedido pedido = TestaPedido.criaPedido();

		Atendente aten = criaAtendente();
		aten.atenderPedido(pedido);

		aten.listar();
		System.out.println();
		pedido.listar();
	}
}
