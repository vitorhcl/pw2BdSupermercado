package br.com.etechoracio.pw2BdSupermercado.testes;

import br.com.etechoracio.pw2BdSupermercado.Supermercado;
import br.com.etechoracio.pw2BdSupermercado.entity.*;

public class TestaCliente {
	public static Cliente criaCliente() {
		Cliente cliente = new Cliente();
		cliente.cadastrar("00001", "12345678909");
		Supermercado.getClientes().add(cliente);
		return cliente;
	}

	public static void main(String[] args) {
		Cliente cliente = TestaCliente.criaCliente();
		cliente.listar();
	}
}
