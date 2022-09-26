package br.com.etechoracio.pw2BdSupermercado;
import java.util.ArrayList;
import java.util.List;

import br.com.etechoracio.pw2BdSupermercado.entity.*;

public class Supermercado {
	private static List<Atendente> atendentes = new ArrayList<Atendente>();
	private static List<Cliente> clientes = new ArrayList<Cliente>();
	private static List<FormPag> formasPag = new ArrayList<FormPag>();
	private static List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
	private static List<ItemPed> itensPed = new ArrayList<ItemPed>();
	private static List<Nf> nfs = new ArrayList<Nf>();
	private static List<Pedido> pedidos = new ArrayList<Pedido>();
	private static List<Produto> produtos = new ArrayList<Produto>();

	public static List<?> getLista(Class<?> T) {
		if (T == Produto.class)
			return Supermercado.produtos;
		else if (T == Fornecedor.class)
			return Supermercado.fornecedores;
		else if (T == Pedido.class)
			return Supermercado.pedidos;
		else if (T == Atendente.class)
			return Supermercado.atendentes;
		else if (T == Cliente.class)
			return Supermercado.clientes;
		else if (T == FormPag.class)
			return Supermercado.formasPag;
		return Supermercado.nfs;
	}

	public static List<Atendente> getAtendentes() {
		return atendentes;
	}

	public static List<Cliente> getClientes() {
		return clientes;
	}

	public static List<FormPag> getFormasPag() {
		return formasPag;
	}

	public static List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public static List<ItemPed> getItensPed() {
		return itensPed;
	}

	public static List<Nf> getNfs() {
		return nfs;
	}

	public static List<Pedido> getPedidos() {
		return pedidos;
	}

	public static List<Produto> getProdutos() {
		return produtos;
	}

	private static <T> boolean listar(Class<T> T, String msgErro) {
		List<T> lista = (List<T>) Supermercado.getLista(T);
		if(T == Produto.class)
			produtos.removeIf(prod -> prod.getEstoque() == 0);
		int i = 0;
		if (lista.size() == 0) {
			System.out.println(msgErro);
			return false;
		}
		for (Object item : lista) {
			System.out.printf("(%d): ", i + 1);
			((Listavel) item).listar();
			System.out.println();
			i++;
		}
		return true;
	}

	public static boolean listar(Class<?> T) {
		String msgErro = "Sem cadastros";
		if (T == Produto.class)
			msgErro = "Nenhum produto foi cadastrado";
		else if (T == Fornecedor.class)
			msgErro = "Nenhum fornecedor foi cadastrado";
		else if (T == Pedido.class)
			msgErro = "Nenhum pedido foi realizado";
		else if (T == Atendente.class)
			msgErro = "Nenhum atendente foi cadastrado";
		else if (T == Cliente.class)
			msgErro = "Nenhum cliente foi cadastrado";
		else if (T == FormPag.class)
			msgErro = "Nenhuma forma de pagamento foi cadastrada";
		else if (T == Nf.class)
			msgErro = "Nenhuma nota fiscal foi emitida";
		return listar(T, msgErro);
	}
}
