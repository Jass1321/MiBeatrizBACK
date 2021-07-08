package com.app.repository.Maestro.Tercero;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Maestro.Tercero.DireccionTercero;
import com.app.model.Maestro.Tercero.Proveedor;

@Repository
public interface DireccionTerceroRepository extends JpaRepository<DireccionTercero, Long>{

	public List<DireccionTercero> findByProveedorId(Proveedor proveedorId);
	
	public Optional<DireccionTercero> findByIdAndProveedorId(Long id, Long proveedorId);

}