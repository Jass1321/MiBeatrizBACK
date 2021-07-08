package com.app.service.Maestro.Tercero;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Maestro.Inventario;
import com.app.model.Maestro.Tercero.DireccionTercero;
import com.app.model.Maestro.Tercero.Proveedor;
import com.app.repository.Maestro.Tercero.DireccionTerceroRepository;

@Service
@Transactional
public class DireccionTerceroService{

	
	@Autowired
	DireccionTerceroRepository direccionServiceImpl;

	
	public List<DireccionTercero> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return (List<DireccionTercero>) direccionServiceImpl.findAll();
	}

	public DireccionTercero findById(Long id) {
		// TODO Auto-generated method stub
		return direccionServiceImpl.findById(id).orElse(null);
	}


	public DireccionTercero save(DireccionTercero direccion) {
		// TODO Auto-generated method stub
		return direccionServiceImpl.save(direccion);
	}


	public List<DireccionTercero> saveDireccion(List<DireccionTercero> direcciones) {
		// TODO Auto-generated method stub
		return (List<DireccionTercero>) direccionServiceImpl.saveAll(direcciones);
	}


	public List<DireccionTercero> findByDireccionId(Proveedor proveedorId) {
		// TODO Auto-generated method stub
		return direccionServiceImpl.findByProveedorId(proveedorId);
	}


	public boolean existDireccion(List<DireccionTercero> direcciones, Long id) {
		 boolean existe = false;
			
			for(int i=0; i<direcciones.size(); i++) {
				DireccionTercero d = direcciones.get(i);
				if(d.getId() != null) {				
					if(d.getId().equals(id)) {
						existe = true;
						break;
					}
				}
			}
			
			return existe;
	}

	
	
}
