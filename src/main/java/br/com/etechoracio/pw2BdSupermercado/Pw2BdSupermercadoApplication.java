package br.com.etechoracio.pw2BdSupermercado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import br.com.etechoracio.pw2BdSupermercado.entity.FormPag;
import br.com.etechoracio.pw2BdSupermercado.repository.AtendenteRepository;
import br.com.etechoracio.pw2BdSupermercado.repository.ClienteRepository;
import br.com.etechoracio.pw2BdSupermercado.repository.FormPagRepository;
import br.com.etechoracio.pw2BdSupermercado.repository.FornecedorRepository;
import br.com.etechoracio.pw2BdSupermercado.repository.ItemPedRepository;
import br.com.etechoracio.pw2BdSupermercado.repository.NfRepository;
import br.com.etechoracio.pw2BdSupermercado.repository.PedidoRepository;
import br.com.etechoracio.pw2BdSupermercado.repository.ProdutoRepository;

@SpringBootApplication
public class Pw2BdSupermercadoApplication implements CommandLineRunner {

	@Autowired
	private AtendenteRepository atendenteRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private FormPagRepository formPagRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Autowired
	private ItemPedRepository itemPedRepository;
	
	@Autowired
	private NfRepository nfRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Pw2BdSupermercadoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//produtoRepository.findAll();
		//pedidoRepository.findAll();
		//itemPedRepository.findAll();
		
		//itemPedRepository.findAll().forEach(itemPed -> itemPed.listar());
		MenuPrincipal menuPrin = new MenuPrincipal(atendenteRepository, clienteRepository, formPagRepository, fornecedorRepository, itemPedRepository, nfRepository, pedidoRepository, produtoRepository);
		menuPrin.main();
	}
}
