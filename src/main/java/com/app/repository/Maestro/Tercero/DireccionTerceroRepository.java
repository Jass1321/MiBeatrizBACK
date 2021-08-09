package com.app.repository.Maestro.Tercero;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Maestro.Tercero.Cliente;
import com.app.model.Maestro.Tercero.DireccionTercero;
import com.app.model.Maestro.Tercero.Proveedor;

@Repository
public interface DireccionTerceroRepository extends JpaRepository<DireccionTercero, Long>{
	
	public List<DireccionTercero> findByProveedorId(Proveedor proveedorId);
	public List<DireccionTercero> findByClienteId(Cliente clienteId);
	
	public void deleteByProveedorId(Proveedor proveedor);
	public void deleteByClienteId(Cliente cliente);
}