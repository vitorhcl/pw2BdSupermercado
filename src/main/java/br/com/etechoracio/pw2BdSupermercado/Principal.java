package br.com.etechoracio.pw2BdSupermercado;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.com.etechoracio.pw2BdSupermercado.entity.*;
import br.com.etechoracio.pw2BdSupermercado.enums.*;
import br.com.etechoracio.pw2BdSupermercado.testes.*;

public class Principal {
	private static Scanner ent = new Scanner(System.in);

	public static int menu(boolean menuPrincipal, String titulo, String... msgs) {
		int escolha = 0;
		try {
			System.out.println(titulo);
			for (int i = 0; i < msgs.length; i++) {
				System.out.print(String.format("(%d): ", i + 1));
				System.out.println(msgs[i]);
			}
			System.out.printf("(0): %s\n", menuPrincipal ? "Sair" : "Voltar");
			escolha = ent.nextInt();
			if (!(0 <= escolha && escolha <= msgs.length)) {
				System.out.println("Opção inválida!");
				escolha = menu(menuPrincipal, titulo, msgs);
			}
		} catch (Exception InputMismatchException) {
			ent.nextLine();
			System.out.println("Digite um número!");
			escolha = menu(menuPrincipal, titulo, msgs);
		}
		return escolha;
	}

	public static void main(String[] args) {
		preencherSupermercado();

		menuPrincipal();
	}

	private static void preencherSupermercado() {
		Supermercado.getFormasPag().add(FormPag.builder().codigo(FormPagEnum.CREDITO).nome("Crédito").build());
		Supermercado.getFormasPag().add(FormPag.builder().codigo(FormPagEnum.DEBITO).nome("Débito").build());
		Supermercado.getFormasPag().add(FormPag.builder().codigo(FormPagEnum.PIX).nome("PIX").build());
		Supermercado.getFormasPag().add(FormPag.builder().codigo(FormPagEnum.VALEREFEICAO).nome("Vale-Refeição").build());

		TestaPedido.criaPedido();
		TestaAtendente.criaAtendente();
	}

	private static void menuPrincipal() {
		int escolha;
		escolha = menu(true, "O que deseja fazer?", "Cadastrar", "Atender pedido", "Listar");
		switch (escolha) {
		case 1:
			menuCadastro();
			break;
		case 2:
			menuAtenderPedido();
			break;
		case 3:
			menuListar();
			break;
		case 0:
			System.out.println("Saindo...");
			break;
		}
		if (escolha != 0)
			menuPrincipal();
		ent.close();
	}

	private static void menuCadastro() {
		int escolha;
		escolha = menu(false, "O que deseja cadastrar?", "Pedido", "Cliente", "Fornecedor", "Fornecimento", "Produto", "Atendente");
		try {
			switch (escolha) {
			case 1:
				try {
					menuCadastroPedido();
				} catch (IllegalArgumentException | InputMismatchException e) {
					ent.nextLine();
					System.out.println("Os dados inseridos são inválidos!");
				}
				break;
			case 2:
				menuCadastroClie();
				break;
			case 3:
				menuCadastroForn();
				break;
			case 4:
				menuCadastroFornecimento();
				break;
			case 5:
				menuCadastroProd();
				break;
			case 6:
				menuCadastroAtendente();
				break;
			}
		} catch (IllegalArgumentException | InputMismatchException e) {
			ent.nextLine();
			System.out.println("Os dados inseridos são inválidos!");
		}
		if (escolha != 0)
			menuCadastro();
	}

	private static void menuListar() {
		int escolha;
		escolha = menu(false, "O que deseja listar?", "Produtos", "Atendentes", "Fornecedores", "Pedidos", "Clientes",
				"Formas de pagamento", "Notas fiscais");
		switch (escolha) {
		case 1:
			Supermercado.listar(Produto.class);
			break;
		case 2:
			Supermercado.listar(Atendente.class);
			break;
		case 3:
			Supermercado.listar(Fornecedor.class);
			break;
		case 4:
			Supermercado.listar(Pedido.class);
			break;
		case 5:
			Supermercado.listar(Cliente.class);
			break;
		case 6:
			Supermercado.listar(FormPag.class);
			break;
		case 7:
			Supermercado.listar(Nf.class);
			break;
		}
		if (escolha != 0)
			menuListar();
	}

	private static <T> int lerIndiceLista(Class<T> T, String msgErro) {
		boolean existe = Supermercado.listar(T);
		if (!existe)
			return -1;
		int num = ent.nextInt();
		if (!(0 <= num && num <= Supermercado.getLista(T).size())) {
			System.out.println(msgErro);
			return lerIndiceLista(T, msgErro);
		}
		return num;
	}

	private static <T> T escolher(Class<T> T, String msgErro) {
		int num = lerIndiceLista(T, msgErro);
		if (num == -1 || num == 0)
			return null;

		T objeto = (T) Supermercado.getLista(T).get(num - 1);
		return (T) objeto;
	}

	private static void menuAtenderPedido() {
		System.out.println("Escolha o atendente:");
		Atendente atendente = escolher(Atendente.class, "Atendente inválido!");
		if (atendente == null)
			return;

		System.out.println("Escolha o pedido:");
		Pedido pedido = escolher(Pedido.class, "Pedido inválido!");
		if (pedido == null)
			return;

		atendente.atenderPedido(pedido);
	}

	private static void menuCadastroPedido() {
		System.out.println("Escolha o cliente:");
		Cliente clie = escolher(Cliente.class, "Cliente inválido");

		Pedido ped = new Pedido();
		boolean existeProds = menuAdicionarItens(ped);
		if (!existeProds)
			return;
		if (ped.getItens().size() == 0) {
			System.out.println("Não há nenhum item no pedido!");
			return;
		}
		String numero;

		System.out.println("Número (6 dígitos):");
		numero = ent.next();

		System.out.println("\nNota fiscal:");
		Nf nf = menuEmitirNf();

		System.out.println("Forma de pagamento:");
		FormPag formPag = escolher(FormPag.class, "Forma de pagamento inválida!");
		if (formPag == null)
			return;

		ped.cadastrar(numero, clie, formPag, nf);
		Supermercado.getPedidos().add(ped);
	}

	private static Nf menuEmitirNf() {
		Nf nf;
		String num, codBar, strDataEmi;
		LocalDateTime dataEmi;
		
		ent.nextLine();

		System.out.println("Número (4 dígitos): ");
		num = ent.nextLine();

		System.out.println("Código de barras EAN-13 (13 dígitos): ");
		codBar = ent.nextLine();

		System.out.println("Data de emissão, a para agora (dd/MM/yyyy HH:mm:ss): ");
		strDataEmi = ent.nextLine();
		
		if (strDataEmi.equals("h")) {
			nf = Nf.builder().codBar(codBar).num(num).build();
		} else {
			dataEmi = new Formatador().data(strDataEmi);
			nf = Nf.builder().codBar(codBar).num(num).dataEmi(dataEmi).build();
		}

		Supermercado.getNfs().add(nf);
		return nf;
	}

	private static boolean menuAdicionarItens(Pedido ped) {
		System.out.println("Adicione um produto ao pedido ou digite 0 para finalizar a compra:");
		int num = lerIndiceLista(Produto.class, "Produto inválido!");

		if (num != 0) {
			ItemPed itemPed = new ItemPed();
			System.out.println("Quantidade:");
			int quantidade = ent.nextInt();

			itemPed.cadastrar(Supermercado.getProdutos().get(num - 1), quantidade);
			ped.adicionarItens(itemPed);
			menuAdicionarItens(ped);
		}
		return true;
	}

	private static void menuCadastroAtendente() {
		Atendente atendente = new Atendente();
		String cpf, nome;
		double slr;
		System.out.print("Nome: ");
		nome = ent.next();

		System.out.print("CPF (só digitos): ");
		cpf = ent.next();

		System.out.print("Salário: ");
		slr = ent.nextDouble();

		atendente.cadastrar(cpf, nome, slr);
		Supermercado.getAtendentes().add(atendente);
	}

	private static void menuCadastroProd() {
		System.out.println("Escolha um fornecedor:");

		Fornecedor forn = escolher(Fornecedor.class, "Fornecedor inválido!");
		if(forn == null)
			return;
		
		Produto prod = new Produto();

		String nome, codBar, categoria;
		int quantidade;
		double precoCusto, precoVenda;

		System.out.print("Nome: ");
		nome = ent.next();

		System.out.print("Código de barras EAN-13 (13 dígitos): ");
		codBar = ent.next();

		System.out.print("Quantidade: ");
		quantidade = ent.nextInt();

		System.out.print("Preço de custo: ");
		precoCusto = ent.nextDouble();

		forn.comprar(prod, quantidade);
		
		System.out.print("Preço de venda: ");
		precoVenda = ent.nextDouble();

		System.out.print("Categoria: ");
		categoria = ent.next();
		prod.cadastrar(nome, codBar, categoria, precoCusto, precoVenda);
		forn.comprar(prod, quantidade);
		
		Supermercado.getProdutos().add(prod);

	}
	
	private static void menuCadastroFornecimento() {
		System.out.println("Escolha um fornecedor:");
		Fornecedor forn = escolher(Fornecedor.class, "Fornecedor inválido");
		if(forn == null)
			return;
		
		System.out.println("Escolha um produto:");
		Produto prod = escolher(Produto.class, "Produto inválido!");
		if(prod == null)
			return;
		
		int quantidade;
		
		System.out.print("Quantidade: ");
		quantidade = ent.nextInt();
		
		forn.comprar(prod, quantidade);	
	}

	private static void menuCadastroForn() {
		Fornecedor forn = new Fornecedor();
		String nome, cnpj;

		System.out.print("Nome: ");
		nome = ent.next();

		System.out.print("CNPJ (só digitos): ");
		cnpj = ent.next();
		forn.cadastrar(nome, cnpj);
		Supermercado.getFornecedores().add(forn);
	}

	private static void menuCadastroClie() {
		Cliente clie = new Cliente();
		String codigo, cpf;
		System.out.print("Código (5 dígitos): ");
		codigo = ent.next();
		System.out.print("CPF (só digitos): ");
		cpf = ent.next();
		clie.cadastrar(codigo, cpf);
		Supermercado.getClientes().add(clie);
	}

}
