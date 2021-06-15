package com.app.service.Maestro.Tercero;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Maestro.Tercero.Cliente;
import com.app.repository.Maestro.Tercero.ClienteRepository;

@Service
@Transactional
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	
	public List<Cliente> list() {
		return clienteRepository.findAll();
	}
	
	public Optional<Cliente> getOne(int id) {
		return clienteRepository.findById(id);
	}
	
	public Optional<Cliente> getByRuc(int ruc) {
		return clienteRepository.findByRuc(ruc);
	}
	
	public void save(Cliente proveedor) {
		clienteRepository.save(proveedor);
	}
	
	public void delete(int id) {
		clienteRepository.deleteById(id);
	}
	
	public boolean existsById(int id) {
		return clienteRepository.existsById(id);
	}
	
	public boolean existsByRuc(int ruc) {
		return clienteRepository.existsByRuc(ruc);
	}
}
