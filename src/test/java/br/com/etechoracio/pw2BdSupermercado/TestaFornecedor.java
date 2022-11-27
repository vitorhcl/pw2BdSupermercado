package br.com.etechoracio.pw2BdSupermercado;

import br.com.etechoracio.pw2BdSupermercado.entity.*;

public class TestaFornecedor {
	public static Fornecedor criaFornecedor() {
		Fornecedor forn = Fornecedor.builder().nome("Camil").cnpj("64904295000103").build();
		return forn;
	}

	public static void main(String[] args) {
		Fornecedor forn = criaFornecedor();
		forn.listar();
	}
}
