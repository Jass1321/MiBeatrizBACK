package com.app.repository.Maestro.Tercero;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Maestro.Tercero.CuentaTercero;
import com.app.model.Maestro.Tercero.Proveedor;

@Repository
public interface CuentaTerceroRepository  extends JpaRepository<CuentaTercero, Long> {

	public List<CuentaTercero> findByProveedorId(Proveedor proveedorId);
	public Optional<CuentaTercero> findByIdAndProveedorId(Long id, Long proveedorId);
	
}
