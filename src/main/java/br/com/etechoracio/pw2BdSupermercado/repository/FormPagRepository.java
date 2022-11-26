package br.com.etechoracio.pw2BdSupermercado.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.etechoracio.pw2BdSupermercado.entity.FormPag;
import br.com.etechoracio.pw2BdSupermercado.enums.FormPagEnum;

public interface FormPagRepository extends JpaRepository<FormPag, FormPagEnum>{
	
}
