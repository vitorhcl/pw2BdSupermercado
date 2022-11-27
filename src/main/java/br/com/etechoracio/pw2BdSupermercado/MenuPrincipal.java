package br.com.etechoracio.pw2BdSupermercado;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.etechoracio.pw2BdSupermercado.entity.*;
import br.com.etechoracio.pw2BdSupermercado.repository.AtendenteRepository;
import br.com.etechoracio.pw2BdSupermercado.repository.ClienteRepository;
import br.com.etechoracio.pw2BdSupermercado.repository.FormPagRepository;
import br.com.etechoracio.pw2BdSupermercado.repository.FornecedorRepository;
import br.com.etechoracio.pw2BdSupermercado.repository.ItemPedRepository;
import br.com.etechoracio.pw2BdSupermercado.repository.NfRepository;
import br.com.etechoracio.pw2BdSupermercado.repository.PedidoRepository;
import br.com.etechoracio.pw2BdSupermercado.repository.ProdutoRepository;

public class MenuPrincipal {
	private Scanner ent = new Scanner(System.in);
	

	public MenuPrincipal(AtendenteRepository atendenteRepository, ClienteRepository clienteRepository,
			FormPagRepository formPagRepository, FornecedorRepository fornecedorRepository,
			ItemPedRepository itemPedRepository, NfRepository nfRepository, PedidoRepository pedidoRepository,
			ProdutoRepository produtoRepository) {
		this.atendenteRepository = atendenteRepository;
		this.clienteRepository = clienteRepository;
		this.formPagRepository = formPagRepository;
		this.fornecedorRepository = fornecedorRepository;
		this.itemPedRepository = itemPedRepository;
		this.nfRepository = nfRepository;
		this.pedidoRepository = pedidoRepository;
		this.produtoRepository = produtoRepository;
	}

	private AtendenteRepository atendenteRepository;
	private ClienteRepository clienteRepository;
	private FormPagRepository formPagRepository;
	private FornecedorRepository fornecedorRepository;
	private ItemPedRepository itemPedRepository;
	private NfRepository nfRepository;
	private PedidoRepository pedidoRepository;
	private ProdutoRepository produtoRepository;
	
	public MenuPrincipal(AtendenteRepository a) {
		
	}

	private int menu(boolean menuPrincipal, String titulo, String... msgs) {
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

	public void main() {
		menuPrincipal();
	}

	private void menuPrincipal() {
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

	private void menuCadastro() {
		int escolha;
		escolha = menu(false, "O que deseja cadastrar?", "Pedido", "Cliente", "Fornecedor", "Produto", "Atendente");
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
				menuCadastroProd();
				break;
			case 5:
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
	
	private <T, ID> boolean listar(JpaRepository<T, ID> repository, String msgErro) {
		List<T> lista = repository.findAll();
		int i = 0;
		if (lista.size() == 0) {
			System.out.println(msgErro);
			return false;
		}
		for (Object item : lista) {
			System.out.printf("(%d): ", i + 1);
			((IListavel) item).listar();
			System.out.println();
			i++;
		}
		return true;
	}

	private void menuListar() {
		int escolha;
		escolha = menu(false, "O que deseja listar?", "Produtos", "Atendentes", "Fornecedores", "Pedidos", "Clientes",
				"Formas de pagamento", "Notas fiscais");
		switch (escolha) {
		case 1:
			listar(produtoRepository, "Nenhum produto foi cadastrado");
			break;
		case 2:
			listar(atendenteRepository, "Nenhum atendente foi cadastrado");
			break;
		case 3:
			listar(fornecedorRepository, "Nenhum fornecedor foi cadastrado");
			break;
		case 4:
			listar(pedidoRepository, "Nenhum pedido foi cadastrado");
			break;
		case 5:
			listar(clienteRepository, "Nenhum cliente foi cadastrado");
			break;
		case 6:
			listar(formPagRepository, "Nenhuma forma de pagamento foi cadastrada");
			break;
		case 7:
			listar(nfRepository, "Nenhuma nota fiscal foi cadastrada");
			break;
		}
		if (escolha != 0)
			menuListar();
	}

	private <T> int lerIndiceLista(Class<T> T, String msgInvalido, String msgVazio) {
		boolean existe = listar(getRepository(T), msgInvalido);
		if (!existe)
			return -1;
		int num = ent.nextInt();
		if (!(0 <= num && num <= getRepository(T).findAll().size())) {
			System.out.println(msgInvalido);
			return lerIndiceLista(T, msgInvalido, msgVazio);
		}
		return num;
	}
	
	private <T, ID> JpaRepository<T, ID> getRepository(Class<?> T) {
		JpaRepository<T, ID> repository = null;
		if (T == Produto.class)
			return (JpaRepository<T, ID>) produtoRepository;
		else if (T == Fornecedor.class)
			return (JpaRepository<T, ID>) fornecedorRepository;
		else if (T == Pedido.class)
			return (JpaRepository<T, ID>) pedidoRepository;
		else if (T == Atendente.class)
			return (JpaRepository<T, ID>) atendenteRepository;
		else if (T == Cliente.class)
			return (JpaRepository<T, ID>) clienteRepository;
		return (JpaRepository<T, ID>) formPagRepository;
	}

	private <T> T escolher(Class<T> T, String msgInvalido, String msgVazio) {
		int num = lerIndiceLista(T, msgInvalido, msgVazio);
		if (num == -1 || num == 0)
			return null;

		T objeto = (T) getRepository(T).findAll().get(num - 1);
		return (T) objeto;
	}

	private void menuAtenderPedido() {
		System.out.println("Escolha o atendente:");
		Atendente atendente = escolher(Atendente.class, "Atendente inválido!", "Nenhum atendente cadastrado");
		if (atendente == null)
			return;

		System.out.println("Escolha o pedido:");
		Pedido pedido = escolher(Pedido.class, "Pedido inválido!", "Nenhum pedido cadastrado");
		if (pedido == null)
			return;

		atendente.atenderPedido(pedido);
	}

	private void menuCadastroPedido() {
		System.out.println("Escolha o cliente:");
		Cliente clie = escolher(Cliente.class, "Cliente inválido", "Nenhum cliente cadastrado");

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
		FormPag formPag = escolher(FormPag.class, "Forma de pagamento inválida!", "Nenhuma forma de pagamento cadastrada");
		if (formPag == null)
			return;

		ped = Pedido.builder().numero(numero).cliente(clie).formPag(formPag).nf(nf).build();
		pedidoRepository.save(ped);
	}

	private Nf menuEmitirNf() {
		Nf nf;
		int numero;
		String codBar, strDataEmi;
		LocalDateTime dataEmi;
		
		ent.nextLine();

		System.out.println("Número (até 4 dígitos): ");
		numero = ent.nextInt();
		ent.nextLine();

		System.out.println("Código de barras EAN-13 (13 dígitos): ");
		codBar = ent.nextLine();

		System.out.println("Data de emissão, a para agora (dd/MM/yyyy HH:mm:ss): ");
		strDataEmi = ent.nextLine();
		
		if (strDataEmi.equals("h")) {
			nf = Nf.builder().codBar(codBar).numero(numero).build();
		} else {
			dataEmi = new Formatador().data(strDataEmi);
			nf = Nf.builder().codBar(codBar).numero(numero).dataEmi(dataEmi).build();
		}

		nfRepository.save(nf);
		return nf;
	}

	private boolean menuAdicionarItens(Pedido ped) {
		System.out.println("Adicione um produto ao pedido ou digite 0 para finalizar a compra:");
		int num = lerIndiceLista(Produto.class, "Produto inválido!", "Nenhum produto cadastrado");

		if (num != 0) {
			ItemPed itemPed = new ItemPed();
			System.out.println("Quantidade:");
			int quantidade = ent.nextInt();

			itemPed.cadastrar(produtoRepository.findAll().get(num - 1), quantidade);
			ped.adicionarItens(itemPed);
			menuAdicionarItens(ped);
		}
		return true;
	}

	private void menuCadastroAtendente() {
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
		atendenteRepository.save(atendente);
	}

	private void menuCadastroProd() {
		System.out.println("Escolha um fornecedor:");

		Fornecedor forn = escolher(Fornecedor.class, "Fornecedor inválido!", "Nenhum fornecedor cadastrado");
		if(forn == null)
			return;
		
		Produto prod;

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
		
		System.out.print("Preço de venda: ");
		precoVenda = ent.nextDouble();

		System.out.print("Categoria: ");
		categoria = ent.next();
		
		prod = Produto.builder().nome(nome).codBar(codBar).estoque(quantidade).precoCusto(precoCusto).precoVenda(precoVenda).categoria(categoria).build();
		forn.comprar(prod, quantidade);
		
		produtoRepository.save(prod);
	}

	private void menuCadastroForn() {
		Fornecedor forn;
		String nome, cnpj;

		System.out.print("Nome: ");
		nome = ent.next();

		System.out.print("CNPJ (só digitos): ");
		cnpj = ent.next();
		forn = Fornecedor.builder().nome(nome).cnpj(cnpj).build();
		fornecedorRepository.save(forn);
	}

	private void menuCadastroClie() {
		Cliente clie = new Cliente();
		String codigo, cpf;
		System.out.print("Código (5 dígitos): ");
		codigo = ent.next();
		System.out.print("CPF (só digitos): ");
		cpf = ent.next();
		clie.cadastrar(codigo, cpf);
		clienteRepository.save(clie);
	}

}
