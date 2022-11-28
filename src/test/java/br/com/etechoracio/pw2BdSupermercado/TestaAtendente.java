package br.com.etechoracio.pw2BdSupermercado;

import br.com.etechoracio.pw2BdSupermercado.entity.*;

public class TestaAtendente {
	public static Atendente criaAtendente() {
		Atendente aten = Atendente.builder().cpf("13032350050").nome("Lúcia").slr(1800).build();
		return aten;
	}

	public static void main(String[] args) {
		Atendente aten = criaAtendente();

		aten.listar();
		System.out.println();
	}
}
