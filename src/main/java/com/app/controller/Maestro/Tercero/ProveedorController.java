package com.app.controller.Maestro.Tercero;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.Maestro.Tercero.ProveedorDTO;
import com.app.model.Maestro.Tercero.ContactoTercero;
import com.app.model.Maestro.Tercero.CuentaTercero;
import com.app.model.Maestro.Tercero.DireccionTercero;
import com.app.model.Maestro.Tercero.Proveedor;
import com.app.repository.Maestro.Tercero.ContactoTerceroRepository;
import com.app.repository.Maestro.Tercero.CuentaTerceroRepository;
import com.app.repository.Maestro.Tercero.DireccionTerceroRepository;
import com.app.service.Maestro.Tercero.ContactoTerceroService;
import com.app.service.Maestro.Tercero.CuentaTerceroService;
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
	CuentaTerceroService cuentaService;
	
	@Autowired
	ContactoTerceroService contactoService;
	
	/*----------REPO----------*/
	@Autowired
	DireccionTerceroRepository direccionRepository;
	
	@Autowired
	ContactoTerceroRepository contactoRepository;
	
	@Autowired
	CuentaTerceroRepository cuentaRepository;
	
	
	/*----------READ----------*/
	//READ BASCIC
	@GetMapping("/listProveedor")
	public ResponseEntity<List<Proveedor>> getlistProveedor(){
		return ResponseEntity.ok(proveedorService.listProveedor());
	}
		
	@GetMapping("/listDireccion")
	public ResponseEntity<List<DireccionTercero>> getlistDireccion(){
		return ResponseEntity.ok(direccionService.listDireccion());
	}
	
	@GetMapping("/listContacto")
	public ResponseEntity<List<ContactoTercero>> getlistContacto(){
		return ResponseEntity.ok(contactoService.listContacto());
	}
	
	@GetMapping("/listCuenta")
	public ResponseEntity<List<CuentaTercero>> getlistCuenta(){
		return ResponseEntity.ok(cuentaService.listCuenta());
	}
	
	//READ WITH PAGE
	@GetMapping("/listProveedorWithPage")
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
	
	//READ ALL PROVEEDOR 
	@GetMapping("/detail/{id}")
	public  ResponseEntity<?> showAll(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();
		
        Proveedor proveedor = proveedorService.findById(id);
        List<DireccionTercero> direcciones = direccionService.findByProveedor(proveedor);
        List<ContactoTercero> contactos = contactoService.findByProveedor(proveedor);
        List<CuentaTercero> cuentas = cuentaService.findByCuenta(proveedor);

        response.put("proveedor", proveedor);
		response.put("direccion", direcciones);
		response.put("contacto", contactos);
	    response.put("cuenta", cuentas);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	//READ DETAIL BY ID
	@GetMapping("/detailById/{id}")
	public Proveedor getProveedoresById(@PathVariable("id")Long id){
		return proveedorService.getProvById(id);
	}
	
	//READ DETAIL BY RUC
	@GetMapping("/detailByRuc/{ruc}")
    public Proveedor getProveedoresByRuc(@PathVariable("ruc") String ruc){
		return proveedorService.getProvByRuc(ruc);
    }
	
	//GENERAR CODIGO
	@GetMapping("/generateID")
    public ResponseEntity<?> generateCodigo() {
		Map<String, Object> response = new HashMap<>();
		response.put("identificador", proveedorService.obtenerCodigo());
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
	
	/*----------CREATE----------*/
	//CREATE PROVEEDOR WITH DIRECCION - CONTACTO - CUENTA
    @PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody ProveedorDTO proveedorDTO){
		
    	Map<String, Object> response = new HashMap<>();
	
        Proveedor proveedorNew = proveedorService.createProveedor(proveedorDTO.getProveedor());
        
        List<DireccionTercero> direcciones = proveedorDTO.getDireccion();
        List<DireccionTercero> direccionNew = null;
		    if(direcciones != null) {
		 	   for(DireccionTercero direccion: direcciones) {
		 		  direccion.setProveedorId(proveedorNew);
		 	   }
		 	   direccionNew = direccionService.saveDireccion(direcciones);
		    }
	    
	    List<ContactoTercero> contactos = proveedorDTO.getContacto();
        List<ContactoTercero> contactoNew = null;
	        if(contactos != null) {
	     	   for(ContactoTercero contacto: contactos) {
	     		  contacto.setProveedorId(proveedorNew);
	     	   }
	     	  contactoNew = contactoService.saveContacto(contactos);
	        }
	        
        List<CuentaTercero> cuentas = proveedorDTO.getCuenta();
        List<CuentaTercero> cuentaNew = null;
	        
	        if(cuentas != null) {
	     	   for(CuentaTercero cuenta: cuentas) {
	     		  cuenta.setProveedorId(proveedorNew);
	     	   }
	     	   cuentaNew = cuentaService.saveCuenta(cuentas);
	        }
	  
        response.put("proveedor", proveedorNew);
        response.put("direccion", direccionNew);
        response.put("contacto", contactoNew);
        response.put("cuenta", cuentaNew);
        
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
	
	/*----------UPDATE----------*/
	//UPDATE PROVEEDOR WITH DIRECCION - CONTACTO - CUENTA
	 @PutMapping("/update/{id}")
	 @ResponseStatus(HttpStatus.CREATED)
	 public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ProveedorDTO proveedorDTO){
		
		 Map<String, Object> response = new HashMap<>();
					
		Proveedor proveedorUp = proveedorDTO.getProveedor();
		Proveedor proveedorNow = proveedorService.findById(id);
		   
		proveedorNow.setRuc(proveedorUp.getRuc());
		proveedorNow.setFechaInicio(proveedorUp.getFechaInicio());
		proveedorNow.setRazonSocial(proveedorUp.getRazonSocial());
		proveedorNow.setRubroActividad(proveedorUp.getRubroActividad());
		proveedorNow.setComentario(proveedorUp.getComentario());
		
		List<DireccionTercero> direccionUp = proveedorDTO.getDireccion();
		List<DireccionTercero> direccionNow = direccionRepository.findByProveedorId(proveedorNow);
        List<DireccionTercero> direccionEdit = direccionService.updateDireccion(direccionUp, direccionNow, proveedorNow);
	        
        List<ContactoTercero>  contactoUp = proveedorDTO.getContacto();
        List<ContactoTercero>  contactoNow = contactoRepository.findByProveedorId(proveedorNow);
        List<ContactoTercero>  contactoEdit = contactoService.updateContacto(contactoUp, contactoNow, proveedorNow);
	        
        List<CuentaTercero>    cuentaUp = proveedorDTO.getCuenta();
        List<CuentaTercero>    cuentaNow = cuentaRepository.findByProveedorId(proveedorNow);
        List<CuentaTercero>    cuentaEdit = cuentaService.updateCuenta(cuentaUp, cuentaNow, proveedorNow);
        	
        Proveedor proveedorEdit = proveedorService.createProveedor(proveedorNow);
      
        response.put("proveedor", proveedorEdit);
        response.put("direccion", direccionEdit);
        response.put("contacto", contactoEdit);
        response.put("cuenta", cuentaEdit);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	 }
	 
	//DELETE PROVEEDOR 
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id){
		Proveedor proveedor = proveedorService.findById(id);
		direccionService.deleteByProveedorId(proveedor);
		contactoService.deleteByProveedorId(proveedor);
		cuentaService.deleteByProveedorId(proveedor);
		return ResponseEntity.ok(!(proveedorService.deleteProveedor(id)!=null));	
    }
	
	@DeleteMapping("/delete/direccion/{id}")
	private ResponseEntity<Boolean> deleteDireccion (@PathVariable("id")Long id){
		direccionService.deleteById(id);
		return ResponseEntity.ok(!(direccionService.findById(id)!=null));	
	}
	
	@DeleteMapping("/delete/contacto/{id}")
	private ResponseEntity<Boolean> deleteContacto (@PathVariable("id")Long id){
		contactoService.deleteById(id);
		return ResponseEntity.ok(!(direccionService.findById(id)!=null));	
	}
	
	@DeleteMapping("/delete/cuenta/{id}")
	private ResponseEntity<Boolean> deleteCuenta (@PathVariable("id")Long id){
		cuentaService.deleteById(id);
		return ResponseEntity.ok(!(cuentaService.findById(id)!=null));	
	}
	
}
