package br.com.etechoracio.pw2BdSupermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.etechoracio.pw2BdSupermercado.entity.ItemPed;
import br.com.etechoracio.pw2BdSupermercado.entity.ItemPedPk;

public interface ItemPedRepository extends JpaRepository<ItemPed, ItemPedPk>{

}
