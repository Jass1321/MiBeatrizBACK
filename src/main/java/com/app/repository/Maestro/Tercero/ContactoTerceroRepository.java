package com.app.repository.Maestro.Tercero;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Maestro.Tercero.ContactoTercero;
import com.app.model.Maestro.Tercero.Proveedor;

@Repository
public interface ContactoTerceroRepository extends JpaRepository<ContactoTercero, Long>{
	
	List<ContactoTercero> findByProveedorId(Proveedor proveedorId);
	Optional<ContactoTercero> findByIdAndProveedorId(Long id, Long proveedorId);

}
