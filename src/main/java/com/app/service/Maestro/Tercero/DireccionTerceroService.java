package com.app.service.Maestro.Tercero;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Maestro.Tercero.Cliente;
import com.app.model.Maestro.Tercero.DireccionTercero;
import com.app.model.Maestro.Tercero.Proveedor;
import com.app.repository.Maestro.Tercero.DireccionTerceroRepository;

@Service
@Transactional
public class DireccionTerceroService{

	@Autowired
	DireccionTerceroRepository direccionRepository;

	/*----------CONDITIONS----------*/
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
	
	/*----------SEARCHS----------*/
	public DireccionTercero findById(Long id) {
		return direccionRepository.findById(id).orElse(null);
	}
	
	public List<DireccionTercero> findByProveedor(Proveedor proveedorId) {
		return direccionRepository.findByProveedorId(proveedorId);
	}

	public List<DireccionTercero> findByCliente(Cliente clienteId) {
		return direccionRepository.findByClienteId(clienteId);
	}
	
	/*----------READ ----------*/
	public List<DireccionTercero> listDireccion() {
		return direccionRepository.findAll();
	}
	
	/*----------CREATE----------*/
	public DireccionTercero save(DireccionTercero direccion) {
		return direccionRepository.save(direccion);
	}

	public List<DireccionTercero> saveDireccion(List<DireccionTercero> direcciones) {
		return (List<DireccionTercero>) direccionRepository.saveAll(direcciones);
	}
	
	/*----------UPDATE----------*/
	public List<DireccionTercero> updateDireccion(List<DireccionTercero> direccionEdit, List<DireccionTercero> direccionNow, Proveedor proveedor) {
		
		int size = direccionNow.size();

		for(int i=0; i<direccionEdit.size(); i++) {
			if (i < size) {
				if(direccionEdit.get(i).getId() != null) {					
					if(existDireccion(direccionNow, direccionEdit.get(i).getId())) {	
						direccionNow.get(i).setDomicilio(direccionEdit.get(i).getDomicilio());
						direccionNow.get(i).setDepartamento(direccionEdit.get(i).getDepartamento());
						direccionNow.get(i).setDistrito(direccionEdit.get(i).getDistrito());
						direccionNow.get(i).setPais(direccionEdit.get(i).getPais());
						direccionNow.get(i).setProvincia(direccionEdit.get(i).getProvincia());
						direccionNow.get(i).setUbigeo(direccionEdit.get(i).getUbigeo());					
					} else {
						direccionEdit.get(i).setProveedorId(proveedor);
						direccionNow.add(direccionEdit.get(i));
					}
				}else {
					direccionEdit.get(i).setProveedorId(proveedor);
					direccionNow.add(direccionEdit.get(i));
				}
			}else {
				direccionEdit.get(i).setProveedorId(proveedor);
				direccionNow.add(direccionEdit.get(i));
			}
		}
		
		List<DireccionTercero> remove = new ArrayList<>();
		for(int i=0; i<direccionNow.size(); i++) {
			if(direccionNow.get(i).getId() != null) {
				if(!existDireccion(direccionEdit, direccionNow.get(i).getId())) {
					remove.add(direccionNow.get(i));
				}
			}
		}
		
		if(remove != null) {			
			for(int i=0; i<remove.size(); i++) {
				direccionNow.remove(remove.get(i));
			}
		}
		
		deleteOK(remove);
		return (List<DireccionTercero>) direccionRepository.saveAll(direccionNow);
	}
	
public List<DireccionTercero> updateDireccion(List<DireccionTercero> direccionEdit, List<DireccionTercero> direccionNow, Cliente cliente) {
		
		int size = direccionNow.size();

		for(int i=0; i<direccionEdit.size(); i++) {
			if (i < size) {
				if(direccionEdit.get(i).getId() != null) {					
					if(existDireccion(direccionNow, direccionEdit.get(i).getId())) {	
						direccionNow.get(i).setDomicilio(direccionEdit.get(i).getDomicilio());
						direccionNow.get(i).setDepartamento(direccionEdit.get(i).getDepartamento());
						direccionNow.get(i).setDistrito(direccionEdit.get(i).getDistrito());
						direccionNow.get(i).setPais(direccionEdit.get(i).getPais());
						direccionNow.get(i).setProvincia(direccionEdit.get(i).getProvincia());
						direccionNow.get(i).setUbigeo(direccionEdit.get(i).getUbigeo());					
					} else {
						direccionEdit.get(i).setClienteId(cliente);
						direccionNow.add(direccionEdit.get(i));
					}
				}else {
					direccionEdit.get(i).setClienteId(cliente);
					direccionNow.add(direccionEdit.get(i));
				}
			}else {
				direccionEdit.get(i).setClienteId(cliente);
				direccionNow.add(direccionEdit.get(i));
			}
		}
		
		List<DireccionTercero> remove = new ArrayList<>();
		for(int i=0; i<direccionNow.size(); i++) {
			if(direccionNow.get(i).getId() != null) {
				if(!existDireccion(direccionEdit, direccionNow.get(i).getId())) {
					remove.add(direccionNow.get(i));
				}
			}
		}
		
		if(remove != null) {			
			for(int i=0; i<remove.size(); i++) {
				direccionNow.remove(remove.get(i));
			}
		}
		
		deleteOK(remove);
		return (List<DireccionTercero>) direccionRepository.saveAll(direccionNow);
	}
	
	/*----------DELETE----------*/
	public void deleteByProveedorId(Proveedor proveedor) {
		direccionRepository.deleteByProveedorId(proveedor);
	}
	public void deleteByClienteId(Cliente cliente) {
		direccionRepository.deleteByClienteId(cliente);
	}
	public void deleteById(Long id) {
		direccionRepository.deleteById(id);
	}
	
	public void deleteOK(List<DireccionTercero> direcciones) {
		direccionRepository.deleteAll(direcciones);
	}
		
}
