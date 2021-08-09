package com.app.service.Maestro.Tercero;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.dto.NotFoundException;
import com.app.model.Maestro.Tercero.Cliente;
import com.app.repository.Maestro.Tercero.ClienteRepository;
import com.app.repository.Maestro.Tercero.ContactoTerceroRepository;
import com.app.repository.Maestro.Tercero.DireccionTerceroRepository;

@Service
@Transactional
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	DireccionTerceroRepository direccionRepository;
	
	@Autowired
	ContactoTerceroRepository contactoRepository;
	
	/*----------CONDITIONS----------*/
	public boolean existsById(Long id) {
		return clienteRepository.existsById(id);
	}
	
	public boolean existsByRuc(String ruc) {
		return clienteRepository.existsByRuc(ruc);
	}
	
	/*----------SEARCHS----------*/
	public Cliente findById(Long id) {
		return clienteRepository.findById(id).orElse(null);
	}
	
	public Cliente getCliById(Long id) {
		Cliente cli = new Cliente();
		cli = clienteRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cliente ID - " + id +"no existe"));
		return cli;
	}
	
	public Cliente getCliByRuc(String ruc) {
		Cliente cli = new Cliente();
		cli = clienteRepository.findByRuc(ruc)
				.orElseThrow(() -> new NotFoundException("Cliente RUC - " + ruc +"no existe"));
		return cli;
	}
	
	public Long obtenerCodigo() {
		Cliente cliente = clienteRepository.findTopByOrderByIdDesc();
		if(cliente == null) {
			return 0L;
		}
		return cliente.getId();
	}
	
	
	/*----------READ BASIC----------*/
	public List<Cliente> listCliente() {
		return (List<Cliente>) clienteRepository.findAll();
	}
	
	/*----------READ WITH PAGE----------*/
	public Page<Cliente> listClienteWithPage(Pageable pageable){
        return clienteRepository.findAll(pageable);
    }
	
	/*----------CREATE----------*/
	public Cliente createCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	/*----------DELETE----------*/
	public ResponseEntity<?> deleteCliente(Long id) {
		Cliente cli = new Cliente();
		cli = clienteRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cliente ID - " + id +"no existe"));
		
		clienteRepository.delete(cli);
		return ResponseEntity.ok().build();
	}
	
	
}
