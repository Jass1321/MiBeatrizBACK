package com.app.service.Maestro.Tercero;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Maestro.Tercero.CuentaTercero;
import com.app.model.Maestro.Tercero.Proveedor;
import com.app.repository.Maestro.Tercero.CuentaTerceroRepository;

@Service
@Transactional
public class CuentaTerceroService {

	@Autowired
	CuentaTerceroRepository cuentaRepository;

	/*----------CONDITIONS----------*/
	public boolean existCuenta(List<CuentaTercero> cuentas, Long id) {
		 boolean existe = false;
			
			for(int i=0; i<cuentas.size(); i++) {
				CuentaTercero c = cuentas.get(i);
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
	public CuentaTercero findById(Long id) {
		return cuentaRepository.findById(id).orElse(null);
	}

	public List<CuentaTercero> findByCuenta(Proveedor proveedorId) {
		return cuentaRepository.findByProveedorId(proveedorId);
	}

	/*----------READ ----------*/
	public List<CuentaTercero> listCuenta() {
		return cuentaRepository.findAll();
	}
	
	/*----------CREATE----------*/
	public CuentaTercero save(CuentaTercero cuenta) {
		return cuentaRepository.save(cuenta);
	}

	public List<CuentaTercero> saveCuenta(List<CuentaTercero> cuentas) {
		return (List<CuentaTercero>) cuentaRepository.saveAll(cuentas);
	}

	/*----------UPDATE----------*/
	public List<CuentaTercero> updateCuenta(List<CuentaTercero> cuentaEdit, List<CuentaTercero> cuentaNow, Proveedor proveedor) {
		
		int size = cuentaNow.size();

		for(int i=0; i<cuentaEdit.size(); i++) {
			if (i < size) {
				if(cuentaNow.get(i).getId() != null) {					
					if(existCuenta(cuentaNow, cuentaEdit.get(i).getId())) {	
						cuentaNow.get(i).setNum(cuentaEdit.get(i).getNum());		
						cuentaNow.get(i).setCci(cuentaEdit.get(i).getCci());		
						cuentaNow.get(i).setMoneda(cuentaEdit.get(i).getMoneda());		
						cuentaNow.get(i).setEntidad(cuentaEdit.get(i).getEntidad());	
						cuentaNow.get(i).setTipoCuenta(cuentaEdit.get(i).getTipoCuenta());		
					} else {
						cuentaEdit.get(i).setProveedorId(proveedor);
						cuentaNow.add(cuentaEdit.get(i));
					}
				}else {
					cuentaEdit.get(i).setProveedorId(proveedor);
					cuentaNow.add(cuentaEdit.get(i));
				}
			}else {
				cuentaEdit.get(i).setProveedorId(proveedor);
				cuentaNow.add(cuentaEdit.get(i));
			}
		}
		
		List<CuentaTercero> remove = new ArrayList<>();
		for(int i=0; i<cuentaNow.size(); i++) {
			if(cuentaNow.get(i).getId() != null) {
				if(!existCuenta(cuentaEdit, cuentaNow.get(i).getId())) {
					remove.add(cuentaNow.get(i));
				}
			}
		}
		
		if(remove != null) {			
			for(int i=0; i<remove.size(); i++) {
				cuentaNow.remove(remove.get(i));
			}
		}
		
		deleteOK(remove);
		return (List<CuentaTercero>) cuentaRepository.saveAll(cuentaNow);
	}
	
	/*----------DELETE----------*/
	public void deleteByProveedorId(Proveedor proveedor) {
		cuentaRepository.deleteByProveedorId(proveedor);
	}

	public void deleteById(Long id) {
		cuentaRepository.deleteById(id);
	}
	
	public void deleteOK(List<CuentaTercero> cuentas) {
		cuentaRepository.deleteAll(cuentas);
	}
	

	
	
}
