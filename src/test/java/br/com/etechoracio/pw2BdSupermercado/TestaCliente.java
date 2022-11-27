package br.com.etechoracio.pw2BdSupermercado;

import br.com.etechoracio.pw2BdSupermercado.entity.Cliente;

public class TestaCliente {
	public static Cliente criaCliente() {
		Cliente cliente = new Cliente();
		cliente.cadastrar("00001", "12345678909");
		return cliente;
	}

	public static void main(String[] args) {
		Cliente cliente = TestaCliente.criaCliente();
		cliente.listar();
	}
}
