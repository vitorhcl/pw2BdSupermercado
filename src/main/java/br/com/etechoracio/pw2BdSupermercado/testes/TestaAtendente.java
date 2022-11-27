package br.com.etechoracio.pw2BdSupermercado.testes;

import br.com.etechoracio.pw2BdSupermercado.entity.*;

public class TestaAtendente {
	public static Atendente criaAtendente() {
		Atendente aten = new Atendente();
		aten.cadastrar("13032350050", "Lúcia", 1800);
		return aten;
	}

	public static void main(String[] args) {
		Atendente aten = criaAtendente();

		aten.listar();
		System.out.println();
	}
}
