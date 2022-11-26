package br.com.etechoracio.pw2BdSupermercado;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Pw2BdSupermercadoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Pw2BdSupermercadoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		MenuPrincipal menuPrin = new MenuPrincipal();
		menuPrin.main();
	}

}
