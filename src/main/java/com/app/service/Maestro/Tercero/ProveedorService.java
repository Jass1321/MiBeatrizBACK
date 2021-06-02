package com.app.service.Maestro.Tercero;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Maestro.Tercero.Proveedor;
import com.app.repository.Maestro.Tercero.ProveedorRepository;

@Service
@Transactional
public class ProveedorService {

	@Autowired
	ProveedorRepository proveedorRepository;
	
	public List<Proveedor> list() {
		return proveedorRepository.findAll();
	}
	
	public Optional<Proveedor> getOne(int id) {
		return proveedorRepository.findById(id);
	}
	
	public Optional<Proveedor> getByRuc(int ruc) {
		return proveedorRepository.findByRuc(ruc);
	}
	
	public void save(Proveedor proveedor) {
		proveedorRepository.save(proveedor);
	}
	
	public void delete(int id) {
		proveedorRepository.deleteById(id);
	}
	
	public boolean existsById(int id) {
		return proveedorRepository.existsById(id);
	}
	
	public boolean existsByRuc(int ruc) {
		return proveedorRepository.existsByRuc(ruc);
	}
}
