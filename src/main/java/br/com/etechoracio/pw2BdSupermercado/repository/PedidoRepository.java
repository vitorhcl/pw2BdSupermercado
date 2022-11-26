package br.com.etechoracio.pw2BdSupermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.etechoracio.pw2BdSupermercado.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, String> {

}
