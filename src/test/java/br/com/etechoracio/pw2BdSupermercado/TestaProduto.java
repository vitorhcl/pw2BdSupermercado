package br.com.etechoracio.pw2BdSupermercado;

import br.com.etechoracio.pw2BdSupermercado.entity.*;

public class TestaProduto {
	static Fornecedor forn = TestaFornecedor.criaFornecedor();

	public static Produto criaProduto1() {
		Produto prod1 = new Produto();
		prod1.cadastrar("Arroz", "7898357410015", "Grãos", 5, 8);

		forn.comprar(prod1, 320);
		Supermercado.getProdutos().add(prod1);
		return prod1;
	}

	public static Produto criaProduto2() {
		Produto prod2 = new Produto();
		prod2.cadastrar("Feijão", "7897652320359", "Grãos", 15, 20);

		forn.comprar(prod2, 370);
		Supermercado.getProdutos().add(prod2);
		return prod2;
	}

	public static void main(String[] args) {
		Produto prod1 = criaProduto1();
		Produto prod2 = criaProduto2();

		System.out.println("Produto 1:");
		prod1.listar();

		System.out.println();

		System.out.println("Produto 2:");
		prod2.listar();
		
		System.out.println();
		
		System.out.println("Fornecedor:");
		forn.listar();
	}
}
