package com.app.repository.Maestro.Catalogo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Maestro.Catalogo.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long>  {

	Optional<Marca> findByNombre(String nombre);
	boolean existsByNombre(String nombre);
}
