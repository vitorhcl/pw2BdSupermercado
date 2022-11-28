package br.com.etechoracio.pw2BdSupermercado;

import br.com.etechoracio.pw2BdSupermercado.entity.Cliente;

public class TestaCliente {
	public static Cliente criaCliente() {
		Cliente cliente = Cliente.builder().codigo("00001").cpf("12345678909").build();
		return cliente;
	}

	public static void main(String[] args) {
		Cliente cliente = TestaCliente.criaCliente();
		cliente.listar();
	}
}
