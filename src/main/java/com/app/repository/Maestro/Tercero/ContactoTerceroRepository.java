package com.app.repository.Maestro.Tercero;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Maestro.Tercero.Cliente;
import com.app.model.Maestro.Tercero.ContactoTercero;
import com.app.model.Maestro.Tercero.Proveedor;

@Repository
public interface ContactoTerceroRepository extends JpaRepository<ContactoTercero, Long>{
	
	public List<ContactoTercero> findByProveedorId(Proveedor proveedorId);
	public List<ContactoTercero> findByClienteId(Cliente clienteId);
	
	public void deleteByProveedorId(Proveedor proveedor);
	public void deleteByClienteId(Cliente cliente);
}
