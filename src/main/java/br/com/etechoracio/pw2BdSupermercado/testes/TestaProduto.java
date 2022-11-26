package br.com.etechoracio.pw2BdSupermercado.testes;

import br.com.etechoracio.pw2BdSupermercado.Supermercado;
import br.com.etechoracio.pw2BdSupermercado.entity.*;

public class TestaProduto {
	static Fornecedor forn = TestaFornecedor.criaFornecedor();

	public static Produto criaProduto1() {
		Produto prod1 = Produto.builder().nome("Arroz").codBar("7898357410015").categoria("Grãos").precoCusto(5).precoVenda(8).build();

		forn.comprar(prod1, 320);
		Supermercado.getProdutos().add(prod1);
		return prod1;
	}

	public static Produto criaProduto2() {
		Produto prod2 = Produto.builder().nome("Feijão").codBar("7897652320359").categoria("Grãos").precoCusto(15).precoVenda(20).build();

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
