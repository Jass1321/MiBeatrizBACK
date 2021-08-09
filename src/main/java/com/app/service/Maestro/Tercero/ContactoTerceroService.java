package com.app.service.Maestro.Tercero;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Maestro.Tercero.Cliente;
import com.app.model.Maestro.Tercero.ContactoTercero;
import com.app.model.Maestro.Tercero.Proveedor;
import com.app.repository.Maestro.Tercero.ContactoTerceroRepository;

@Service
@Transactional
public class ContactoTerceroService {

	@Autowired
	ContactoTerceroRepository contactoRepository;

	/*----------CONDITIONS----------*/
	public boolean existContacto(List<ContactoTercero> contactos, Long id) {
		 boolean existe = false;
			
			for(int i=0; i<contactos.size(); i++) {
				ContactoTercero c = contactos.get(i);
				if(c.getId() != null) {				
					if(c.getId().equals(id)) {
						existe = true;
						break;
					}
				}
			}
			return existe;
	}
	
	/*----------SEARCHS----------*/
	public ContactoTercero findById(Long id) {
		return contactoRepository.findById(id).orElse(null);
	}

	public List<ContactoTercero> findByProveedor(Proveedor proveedorId) {
		return contactoRepository.findByProveedorId(proveedorId);
	}
	public List<ContactoTercero> findByCliente(Cliente clienteId) {
		return contactoRepository.findByClienteId(clienteId);
	}
	
	/*----------READ ----------*/
	public List<ContactoTercero> listContacto() {
		return contactoRepository.findAll();
	}
	
	/*----------CREATE----------*/
	public ContactoTercero save(ContactoTercero cuenta) {
		return contactoRepository.save(cuenta);
	}
	
	public List<ContactoTercero> saveContacto(List<ContactoTercero> contactos) {
		return (List<ContactoTercero>) contactoRepository.saveAll(contactos);
	}

	/*----------UPDATE----------*/
	public List<ContactoTercero> updateContacto(List<ContactoTercero> contactoEdit, List<ContactoTercero> contactoNow, Proveedor proveedor) {
		
		int size = contactoNow.size();

		for(int i=0; i<contactoEdit.size(); i++) {
			if (i < size) {
				if(contactoNow.get(i).getId() != null) {					
					if(existContacto(contactoNow, contactoEdit.get(i).getId())) {	
						contactoNow.get(i).setNombre(contactoEdit.get(i).getNombre());		
						contactoNow.get(i).setCargo(contactoEdit.get(i).getCargo());		
						contactoNow.get(i).setCorreo(contactoEdit.get(i).getCorreo());		
						contactoNow.get(i).setTelefono(contactoEdit.get(i).getTelefono());		
					} else {
						contactoEdit.get(i).setProveedorId(proveedor);
						contactoNow.add(contactoEdit.get(i));
					}
				}else {
					contactoEdit.get(i).setProveedorId(proveedor);
					contactoNow.add(contactoEdit.get(i));
				}
			}else {
				contactoEdit.get(i).setProveedorId(proveedor);
				contactoNow.add(contactoEdit.get(i));
			}
		}
		
		List<ContactoTercero> remove = new ArrayList<>();
		for(int i=0; i<contactoNow.size(); i++) {
			if(contactoNow.get(i).getId() != null) {
				if(!existContacto(contactoEdit, contactoNow.get(i).getId())) {
					remove.add(contactoNow.get(i));
				}
			}
		}
		
		if(remove != null) {			
			for(int i=0; i<remove.size(); i++) {
				contactoNow.remove(remove.get(i));
			}
		}
		
		deleteOK(remove);
		return (List<ContactoTercero>) contactoRepository.saveAll(contactoNow);
	}
	
	public List<ContactoTercero> updateContacto(List<ContactoTercero> contactoEdit, List<ContactoTercero> contactoNow, Cliente cliente) {
		
		int size = contactoNow.size();

		for(int i=0; i<contactoEdit.size(); i++) {
			if (i < size) {
				if(contactoNow.get(i).getId() != null) {					
					if(existContacto(contactoNow, contactoEdit.get(i).getId())) {	
						contactoNow.get(i).setNombre(contactoEdit.get(i).getNombre());		
						contactoNow.get(i).setCargo(contactoEdit.get(i).getCargo());		
						contactoNow.get(i).setCorreo(contactoEdit.get(i).getCorreo());		
						contactoNow.get(i).setTelefono(contactoEdit.get(i).getTelefono());		
					} else {
						contactoEdit.get(i).setClienteId(cliente);
						contactoNow.add(contactoEdit.get(i));
					}
				}else {
					contactoEdit.get(i).setClienteId(cliente);
					contactoNow.add(contactoEdit.get(i));
				}
			}else {
				contactoEdit.get(i).setClienteId(cliente);
				contactoNow.add(contactoEdit.get(i));
			}
		}
		
		List<ContactoTercero> remove = new ArrayList<>();
		for(int i=0; i<contactoNow.size(); i++) {
			if(contactoNow.get(i).getId() != null) {
				if(!existContacto(contactoEdit, contactoNow.get(i).getId())) {
					remove.add(contactoNow.get(i));
				}
			}
		}
		
		if(remove != null) {			
			for(int i=0; i<remove.size(); i++) {
				contactoNow.remove(remove.get(i));
			}
		}
		
		deleteOK(remove);
		return (List<ContactoTercero>) contactoRepository.saveAll(contactoNow);
	}
	
	/*----------DELETE----------*/
	public void deleteByProveedorId(Proveedor proveedor) {
		contactoRepository.deleteByProveedorId(proveedor);
	}
	
	public void deleteByClienteId(Cliente cliente) {
		contactoRepository.deleteByClienteId(cliente);
	}

	public void deleteById(Long id) {
		contactoRepository.deleteById(id);
	}
	
	public void deleteOK(List<ContactoTercero> contactos) {
		contactoRepository.deleteAll(contactos);
	}
}

