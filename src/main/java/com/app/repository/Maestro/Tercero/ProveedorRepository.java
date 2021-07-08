package com.app.repository.Maestro.Tercero;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Maestro.Almacen;
import com.app.model.Maestro.Tercero.Cliente;
import com.app.model.Maestro.Tercero.Proveedor;

@Repository
public interface ProveedorRepository extends  JpaRepository<Proveedor, Long>{
	
	Optional<Proveedor> findByRuc(String ruc);
	boolean existsByRuc(String ruc);
	
}