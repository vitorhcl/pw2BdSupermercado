package br.com.etechoracio.pw2BdSupermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.etechoracio.pw2BdSupermercado.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String>{

}
