package com.app.controller.Maestro.Tercero;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.Maestro.Tercero.AlmacenDTO;
import com.app.dto.Maestro.Tercero.ProveedorDTO;
import com.app.model.Maestro.Almacen;
import com.app.model.Maestro.CuentaBancaria;
import com.app.model.Maestro.Inventario;
import com.app.model.Maestro.Tercero.ContactoTercero;
import com.app.model.Maestro.Tercero.CuentaTercero;
import com.app.model.Maestro.Tercero.DireccionTercero;
import com.app.model.Maestro.Tercero.Proveedor;
import com.app.repository.Maestro.Tercero.ContactoTerceroRepository;
import com.app.repository.Maestro.Tercero.CuentaTerceroRepository;
import com.app.repository.Maestro.Tercero.DireccionTerceroRepository;
import com.app.service.Maestro.Tercero.DireccionTerceroService;
import com.app.service.Maestro.Tercero.ProveedorService;

@RestController
@RequestMapping("/proveedores")
@CrossOrigin(origins = "http://localhost:4200")
public class ProveedorController {
	
	@Autowired
	ProveedorService proveedorService;
	
	@Autowired
	DireccionTerceroService direccionService;
	
	@Autowired
	DireccionTerceroRepository direccionRepository;
	
	@Autowired
	ContactoTerceroRepository contactoRepository;
	
	@Autowired
	CuentaTerceroRepository cuentaRepository;
	
	
	/*----------READ----------*/
	//READ BASCIC
	@GetMapping("/listSelectProveedor")
	public ResponseEntity<List<Proveedor>> getlistProveedor(){
		return ResponseEntity.ok(proveedorService.listProveedor());
	}
		
	@GetMapping("/listDireccion")
	public ResponseEntity<List<DireccionTercero>> getlistDireccion(){
		return ResponseEntity.ok(proveedorService.listDireccion());
	}
	
	@GetMapping("/listContacto")
	public ResponseEntity<List<ContactoTercero>> getlistContacto(){
		return ResponseEntity.ok(proveedorService.listContacto());
	}
	
	@GetMapping("/listCuenta")
	public ResponseEntity<List<CuentaTercero>> getlistCuenta(){
		return ResponseEntity.ok(proveedorService.listCuenta());
	}
	
	//READ WITH PAGE
	@GetMapping("/listProveedor")
	public ResponseEntity<Page<Proveedor>> listAllProveedor(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10")int size,
			@RequestParam(defaultValue = "nombre") String order,
		    @RequestParam(defaultValue = "true") boolean asc
    ){
		Page<Proveedor> proveedor = proveedorService.listProveedorWithPage(
			PageRequest.of(page,size, Sort.by(order))
		);
		if(!asc)
            proveedor = proveedorService.listProveedorWithPage(
                    PageRequest.of(page, size, Sort.by(order).descending()));
		return new ResponseEntity<Page<Proveedor>>(proveedor,  HttpStatus.OK);
	}
	
	//READ DETAIL BY ID
	@GetMapping("/detail/{id}")
	public Proveedor getProveedoresById(@PathVariable("id")Long id){
		return proveedorService.getProvById(id);
	}
	/*
	@GetMapping("/detail/{proveedorId}/direcciones")
	public List<DireccionTercero> getDireccionesByProvId(
			@PathVariable("proveedorId")Long proveedorId){
		return direccionRepository.findByProveedorId(proveedorId);
	}
	
	@GetMapping("/detail/{proveedorId}/contactos")
	public List<ContactoTercero> getContactosByProvId(
			@PathVariable("proveedorId")Long proveedorId){
		return contactoRepository.findByProveedorId(proveedorId);
	}
	
	@GetMapping("/detail/{proveedorId}/cuentas")
	public List<CuentaTercero> getCuentasByProvId(
			@PathVariable("proveedorId")Long proveedorId){
		return cuentaRepository.findByProveedorId(proveedorId);
	}
	*/
	@GetMapping("/detailAll/{id}")
	public  ResponseEntity<?> showAll(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		

        Proveedor proveedor = proveedorService.findProvById(id);
        List<DireccionTercero> direcciones = direccionService.findByDireccionId(proveedor);

        response.put("proveedor", proveedor);
		response.put("direccion", direcciones);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	//READ DETAIL BY RUC
	@GetMapping("/detailruc/{ruc}")
    public Proveedor getProveedoresByRuc(@PathVariable("ruc") String ruc){
		return proveedorService.getProvByRuc(ruc);
    }
	
	
	/*----------CREATE----------*/
	//CREATE PROVEEDOR WITH DIRECCION

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody ProveedorDTO proveedorDTO){
		
		Map<String, Object> response = new HashMap<>();
	
        Proveedor proveedorNew = proveedorService.createProveedor(proveedorDTO.getProveedor());
        
        List<DireccionTercero> direcciones = proveedorDTO.getDirecciones();
        List<DireccionTercero> direccionNew = null;
	    if(direcciones != null) {
	 	   for(DireccionTercero direccion: direcciones) {
	 		  direccion.setProveedorId(proveedorNew);
	 	   }
	 	   direccionNew = direccionService.saveDireccion(direcciones);
	    }
	    
	    List<ContactoTercero> contactoList = proveedorDTO.getContactos();
        List<ContactoTercero> contacto = null;
	        
	        if(contactoList != null) {
	     	   for(ContactoTercero contactos: contactoList) {
	     		  contactos.setProveedorId(proveedorNew);
	     	   }
	     	  contacto = proveedorService.saveContacto(contactoList);
	        }
	        
        List<CuentaTercero> cuentaList = proveedorDTO.getCuentas();
        List<CuentaTercero> cuenta = null;
	        
	        if(cuentaList != null) {
	     	   for(CuentaTercero cuentas: cuentaList) {
	     		  cuentas.setProveedorId(proveedorNew);
	     	   }
	     	   cuenta = proveedorService.saveCuenta(cuentaList);
	        }
	  
        response.put("proveedor", proveedorNew);
        response.put("direccion", direccionNew);
        response.put("contacto", contacto);
        response.put("cuenta", cuenta);
        
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
	
	@PostMapping("/save/direccion")
	private ResponseEntity<DireccionTercero> saveDirecciones(@RequestBody DireccionTercero direccion){
		try {
			DireccionTercero direGua = proveedorService.saveDir(direccion);		
		return ResponseEntity.created(new URI("/direccion"+direGua.getId())).body(direGua);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}	
	}
	
	/*UPDATE PROVEEDOR WITH DIRECCION
	 @PutMapping("/update/{id}")
	 public ResponseEntity<?> update(@PathVariable("id")long id,@RequestBody ProveedorDTO proveedorDTO){
		
		Map<String, Object> response = new HashMap<>();
					
		Proveedor proveedorUp = proveedorService.updateProveedor(id,proveedorDTO.getProveedor());
	        
		List<DireccionTercero> direccionList = proveedorDTO.getDireccionTercero();
        List<DireccionTercero> direccion = null;
	        
	        if(direccionList != null) {
	     	   for(DireccionTercero direcciones: direccionList) {
	     		   direcciones.setProveedor(proveedorUp);
	     	   }
	     	   direccion = proveedorService.saveDireccion(direccionList);
	        }

        List<ContactoTercero> contactoList = proveedorDTO.getContactos();
        List<ContactoTercero> contacto = null;
	        
	        if(contactoList != null) {
	     	   for(ContactoTercero contactos: contactoList) {
	     		  contactos.setProveedor(proveedorUp);
	     	   }
	     	  contacto = proveedorService.saveContacto(contactoList);
	        }
	        
        List<CuentaTercero> cuentaList = proveedorDTO.getCuentas();
        List<CuentaTercero> cuenta = null;
	        
	        if(cuentaList != null) {
	     	   for(CuentaTercero cuentas: cuentaList) {
	     		  cuentas.setProveedor(proveedorUp);
	     	   }
	     	   cuenta = proveedorService.saveCuenta(cuentaList);
	        }
        Proveedor proveedor = proveedorService.createProveedor(proveedorUp);
      
        response.put("proveedor", proveedor);
        response.put("direccion", direccion);
        response.put("contacto", contacto);
        response.put("cuenta", cuenta);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
 
	 }*/
	//DELETE PROVEEDOR 
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id){
        return proveedorService.deleteProveedor(id);
    }
	
	@DeleteMapping("/delete/direccion/{id}")
	private ResponseEntity<Boolean> deleteDireccion (@PathVariable("id")Long id){
		proveedorService.deleteByCuentaId(id);
		return ResponseEntity.ok(!(proveedorService.findDireccionById(id)!=null));	
	}
	
	@DeleteMapping("/delete/contacto/{id}")
	private ResponseEntity<Boolean> deleteContacto (@PathVariable("id")Long id){
		proveedorService.deleteByCuentaId(id);
		return ResponseEntity.ok(!(proveedorService.findContactoById(id)!=null));	
	}
	
	@DeleteMapping("/delete/cuenta/{id}")
	private ResponseEntity<Boolean> deleteCuenta (@PathVariable("id")Long id){
		proveedorService.deleteByCuentaId(id);
		return ResponseEntity.ok(!(proveedorService.findCuentaById(id)!=null));	
	}
	
}
