package com.app.service.Maestro.Tercero;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.NotFoundException;
import com.app.model.Maestro.Almacen;
import com.app.model.Maestro.Tercero.ContactoTercero;
import com.app.model.Maestro.Tercero.CuentaTercero;
import com.app.model.Maestro.Tercero.DireccionTercero;
import com.app.model.Maestro.Tercero.Proveedor;
import com.app.repository.Maestro.Tercero.ContactoTerceroRepository;
import com.app.repository.Maestro.Tercero.CuentaTerceroRepository;
import com.app.repository.Maestro.Tercero.DireccionTerceroRepository;
import com.app.repository.Maestro.Tercero.ProveedorRepository;

@Service
@Transactional
public class ProveedorService{


	@Autowired
	ProveedorRepository proveedorRepository;
	
	@Autowired
	DireccionTerceroRepository direccionRepository;
	
	@Autowired
	ContactoTerceroRepository contactoRepository;
	
	@Autowired
	CuentaTerceroRepository cuentaRepository;
	

	/*----------READ BASIC----------*/
	public List<Proveedor> listProveedor() {
		return proveedorRepository.findAll();
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
	
	/*----------READ WITH PAGE----------*/
	public Page<Proveedor> listProveedorWithPage(Pageable pageable){
        return proveedorRepository.findAll(pageable);
    }
	
	/*----------CONDITIONS----------*/
	public boolean existsById(Long id) {
		return proveedorRepository.existsById(id);
	}
	
	public boolean existsByRuc(String ruc) {
		return proveedorRepository.existsByRuc(ruc);
	}
	
	/*----------SEARCHS----------*/
	public Proveedor getProvById(Long id) {
		Proveedor prov = new Proveedor();
		prov = proveedorRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Proveedor ID - " + id +"no existe"));
		return prov;
	}
	
	public Proveedor findProvById(Long id) {
		return proveedorRepository.findById(id).orElse(null);
	}
	
	public Proveedor getProvByRuc(String ruc) {
		Proveedor prov = new Proveedor();
		prov = proveedorRepository.findByRuc(ruc)
				.orElseThrow(() -> new NotFoundException("Proveedor RUC - " + ruc +"no existe"));
		return prov;
	}


	public Optional<DireccionTercero> findDireccionById(Long id) {
		return null;
	}
	
	public Optional<ContactoTercero> findContactoById(Long id) {
		return null;
	}
	
	public Optional<CuentaTercero> findCuentaById(Long id) {
		return null;
	}
	
	
	/*----------CREATE----------*/
	public Proveedor createProveedor(Proveedor proveedor) {
		return proveedorRepository.save(proveedor);
	}

	public List<DireccionTercero> saveDireccion(List<DireccionTercero> direcciones) {
		return direccionRepository.saveAll(direcciones);
	}
	
	public DireccionTercero saveDir(DireccionTercero direcciones) {
		return direccionRepository.save(direcciones);
	}
	
	public List<ContactoTercero> saveContacto(List<ContactoTercero> contacto) {
		return contactoRepository.saveAll(contacto);
	}
	
	public List<CuentaTercero> saveCuenta(List<CuentaTercero> cuenta) {
		return cuentaRepository.saveAll(cuenta);
	}
	
	/*----------UPDATE----------*/
	public Proveedor updateProveedor(Long id, Proveedor provDTO) {
		Proveedor prov = new Proveedor();
		prov = proveedorRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Proveedor ID - " + id +"no existe"));
		prov.setRuc(provDTO.getRuc());
		prov.setFechaInicio(provDTO.getFechaInicio());
		prov.setRazonSocial(provDTO.getRazonSocial());
		prov.setRubroActividad(provDTO.getRubroActividad());
		prov.setComentario(provDTO.getComentario());
		
		Proveedor provUp = proveedorRepository.save(prov);
		return provUp;
	}
	
	/*----------DELETE----------*/
	public ResponseEntity<?> deleteProveedor(Long id) {
		Proveedor prov = new Proveedor();
		prov = proveedorRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Proveedor ID - " + id +"no existe"));
		
		proveedorRepository.delete(prov);
		return ResponseEntity.ok().build();
	}
	
	public void deleteByDireccionId(Long id) {
		direccionRepository.deleteById(id);
	}
	
	public void deleteByContactoId(Long id) {
		cuentaRepository.deleteById(id);
	}
	
	public void deleteByCuentaId(Long id) {
		contactoRepository.deleteById(id);;
	}

	

}
