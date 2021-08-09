package com.app.repository.Maestro.Tercero;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Maestro.Tercero.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>, PagingAndSortingRepository<Cliente, Long>{
	
	Optional<Cliente> findByRuc(String ruc);
	boolean existsByRuc(String ruc);
	
	Cliente findTopByOrderByIdDesc();
}