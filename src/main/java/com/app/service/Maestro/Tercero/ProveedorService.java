package com.app.service.Maestro.Tercero;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.NotFoundException;
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
	
	/*----------CONDITIONS----------*/
	public boolean existsById(Long id) {
		return proveedorRepository.existsById(id);
	}
	
	public boolean existsByRuc(String ruc) {
		return proveedorRepository.existsByRuc(ruc);
	}
	
	/*----------SEARCHS----------*/
	public Proveedor findById(Long id) {
		return proveedorRepository.findById(id).orElse(null);
	}
	
	public Proveedor getProvById(Long id) {
		Proveedor prov = new Proveedor();
		prov = proveedorRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Proveedor ID - " + id +"no existe"));
		return prov;
	}
	
	public Proveedor getProvByRuc(String ruc) {
		Proveedor prov = new Proveedor();
		prov = proveedorRepository.findByRuc(ruc)
				.orElseThrow(() -> new NotFoundException("Proveedor RUC - " + ruc +"no existe"));
		return prov;
	}
	
	public Long obtenerCodigo() {
		Proveedor proveedor = proveedorRepository.findTopByOrderByIdDesc();
		if(proveedor == null) {
			return 0L;
		}
		return proveedor.getId();
	}
	
	/*----------READ BASIC----------*/
	public List<Proveedor> listProveedor() {
		return (List<Proveedor>) proveedorRepository.findAll();
	}
	
	/*----------READ WITH PAGE----------*/
	public Page<Proveedor> listProveedorWithPage(Pageable pageable){
        return proveedorRepository.findAll(pageable);
    }
	
	/*----------CREATE----------*/
	public Proveedor createProveedor(Proveedor proveedor) {
		return proveedorRepository.save(proveedor);
	}
	
	/*----------UPDATE-don't use----------*/
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

}
