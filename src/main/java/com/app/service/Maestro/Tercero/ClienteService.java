package com.app.service.Maestro.Tercero;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.dto.NotFoundException;
import com.app.model.Maestro.Tercero.Cliente;
import com.app.model.Maestro.Tercero.ContactoTercero;
import com.app.model.Maestro.Tercero.CuentaTercero;
import com.app.model.Maestro.Tercero.DireccionTercero;
import com.app.repository.Maestro.Tercero.ClienteRepository;
import com.app.repository.Maestro.Tercero.ContactoTerceroRepository;
import com.app.repository.Maestro.Tercero.CuentaTerceroRepository;
@Service
@Transactional
public class ClienteService {
	
	/*
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	DireccionTerceroRepository direccionRepository;
	
	@Autowired
	ContactoTerceroRepository contactoRepository;
	
	@Autowired
	CuentaTerceroRepository cuentaRepository;
	
	/*----------READ BASIC----------
	public List<Cliente> listCliente() {
		return clienteRepository.findAll();
	}
	
	public List<DireccionTercero> listDireccion() {
		return direccionRepository.findAll();
	}
	
	public List<ContactoTercero> listContacto() {
		return contactoRepository.findAll();
	}
	
	public List<CuentaTercero> listCuenta() {
		return cuentaRepository.findAll();
	}
	
	/*----------READ WITH PAGE----------
	public Page<Cliente> listClienteWithPage(Pageable pageable){
        return clienteRepository.findAll(pageable);
    }
	
	/*----------CONDITIONS----------
	public boolean existsById(Long id) {
		return clienteRepository.existsById(id);
	}
	
	public boolean existsByRuc(String ruc) {
		return clienteRepository.existsByRuc(ruc);
	}
	*/
	/*----------SEARCHS----------
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
	
	public Optional<DireccionTercero> findDireccionById(Long id) {
		return null;
	}
	
	public Optional<ContactoTercero> findContactoById(Long id) {
		return null;
	}
	
	*/

	/*----------CREATE----------
	public Cliente createCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public List<DireccionTercero> saveDireccion(List<DireccionTercero> direccion) {
		return direccionRepository.saveAll(direccion);
	}
	
	public List<ContactoTercero> saveContacto(List<ContactoTercero> contacto) {
		return contactoRepository.saveAll(contacto);
	}
*/
	/*----------UPDATE----------
	public Cliente updateCliente(Long id, Cliente cliDTO) {
		Cliente cli = new Cliente();
		cli = clienteRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cliente ID - " + id +"no existe"));
		cli.setRuc(cliDTO.getRuc());
		cli.setFechaInicio(cliDTO.getFechaInicio());
		cli.setRazonSocial(cliDTO.getRazonSocial());
		cli.setRubroActividad(cliDTO.getRubroActividad());
		cli.setComentario(cliDTO.getComentario());
		
		Cliente cliUp = clienteRepository.save(cli);
		return cliUp;
	}
	*/
	
	/*----------DELETE----------
	public ResponseEntity<?> deleteCliente(Long id) {
		Cliente cli = new Cliente();
		cli = clienteRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Cliente ID - " + id +"no existe"));
		
		clienteRepository.delete(cli);
		return ResponseEntity.ok().build();
	}
	
	public void deleteByDireccionId(Long id) {
		direccionRepository.deleteById(id);
	}
	
	public void deleteByContactoId(Long id) {
		cuentaRepository.deleteById(id);
	}
	
	*/
}
