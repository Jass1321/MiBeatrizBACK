package com.app.repository.Maestro.Catalogo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Maestro.Catalogo.Servicio;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

	List<Servicio> findByTipoId(Long tipoId);
	Optional<Servicio> findByIdAndTipoId(Long id, Long tipoId);
}
