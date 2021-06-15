package com.app.repository.Maestro.Organizacion;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Maestro.Organizacion.Area;

@Repository
public interface AreaRepository extends JpaRepository<Area, Long>{
	
	List<Area> findByDepId(Long depId);
	Optional<Area> findByIdAndDepId(Long id, Long depId);
}
