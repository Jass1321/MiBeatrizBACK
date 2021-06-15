package com.app.service.Maestro.Tercero;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Maestro.Tercero.Direccion;
import com.app.model.Maestro.Tercero.Proveedor;
import com.app.repository.Maestro.Tercero.DireccionRepository;
import com.app.repository.Maestro.Tercero.ProveedorRepository;

@Service
@Transactional
public class ProveedorService {

	@Autowired
	ProveedorRepository proveedorRepository;
	
	@Autowired
	DireccionRepository direccionRepository;
	
	/*----------READ BASIC----------*/
	public List<Proveedor> listProveedor() {
		return proveedorRepository.findAll();
	}
	/*----------READ WITH PAGE----------*/
	public Page<Proveedor> listWithPage(Pageable pageable){
        return proveedorRepository.findAll(pageable);
    }
	/*----------CONDITIONS----------*/
	public boolean existsById(Long id) {
		return proveedorRepository.existsById(id);
	}
	
	public boolean existsByRuc(int ruc) {
		return proveedorRepository.existsByRuc(ruc);
	}
	/*----------SEARCHS----------*/
	public Optional<Proveedor> findById(Long id) {
		return proveedorRepository.findById(id);
	}

	public Optional<Proveedor> getByRuc(int ruc) {
		return proveedorRepository.findByRuc(ruc);
	}
	
	public Proveedor getOne(Long id) {
		return proveedorRepository.findById(id).orElse(null);
	}
	/*----------CREATE----------*/
	public Proveedor save(Proveedor proveedor) {
		return proveedorRepository.save(proveedor);
	}
	/*----------DELETE----------*/
	public void deleteProveedor(Long id) {
		proveedorRepository.deleteById(id);
	}
	
	/*----------DIRECCION---------*/
	public List<Direccion> listDireccion() {
		return direccionRepository.findAll();
	}
	public List<Direccion> saveDireccion(Set<Direccion> direccion) {
		return direccionRepository.saveAll(direccion);
	}
}
