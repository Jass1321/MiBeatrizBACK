package com.app.repository.Maestro.Tercero;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Maestro.Tercero.Proveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer>{
	Optional<Proveedor> findByRuc(int ruc);
	boolean existsByRuc(int ruc);
}
