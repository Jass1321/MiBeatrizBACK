package com.app.repository.Maestro.Catalogo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Maestro.Catalogo.Subfamilia;

@Repository
public interface SubfamiliaRepository extends JpaRepository<Subfamilia, Long> {
	List<Subfamilia> findByFamiliaId(Long familiaId);
	Optional<Subfamilia> findByIdAndFamiliaId(Long id, Long familiad);
}
