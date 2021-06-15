package com.app.controller.Maestro.Tercero;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.Mensaje;
import com.app.dto.Maestro.Tercero.ProveedorDTO;
import com.app.model.Maestro.Tercero.Direccion;
import com.app.model.Maestro.Tercero.Proveedor;
import com.app.service.Maestro.Tercero.ProveedorService;

@RestController
@RequestMapping("/proveedor")
@CrossOrigin(origins = "http://localhost:4200")
public class ProveedorController {
	
	@Autowired
	ProveedorService proveedorService;
	
	/*
	//READ BASCIC
	@GetMapping("/list")
	public ResponseEntity<List<Proveedor>> list(){
		List<Proveedor> list = proveedorService.listProveedor();
		return new ResponseEntity(list, HttpStatus.OK);
	}
	*/
	
	//READ WITH PAGE
	@GetMapping("/list")
	public ResponseEntity<Page<Proveedor>> list(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10")int size,
			@RequestParam(defaultValue = "nombre") String order,
		    @RequestParam(defaultValue = "true") boolean asc
    ){
		Page<Proveedor> proveedores = proveedorService.listWithPage(
			PageRequest.of(page,size, Sort.by(order))
		);
		if(!asc)
            proveedores = proveedorService.listWithPage(
                    PageRequest.of(page, size, Sort.by(order).descending()));
		return new ResponseEntity<Page<Proveedor>>(proveedores,  HttpStatus.OK);
	}
	//READ DETAIL BY ID
	@GetMapping("/detail/{id}")
	public ResponseEntity<Proveedor> getById(@PathVariable("id")Long id){
		if(!proveedorService.existsById(id)) {
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		}
		
	    Proveedor proveedor = proveedorService.findById(id).get();
		return new ResponseEntity(proveedor, HttpStatus.OK);
	}
	
	//READ DETAIL BY RUC
	@GetMapping("/detailruc/{ruc}")
    public ResponseEntity<Proveedor> getByRuc(@PathVariable("ruc") int ruc){
		if(!proveedorService.existsByRuc(ruc))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		
        Proveedor proveedor = proveedorService.getByRuc(ruc).get();
        return new ResponseEntity(proveedor, HttpStatus.OK);
    }
	
	//CREATE PROVEEDOR WITH DIRECCION
	@PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProveedorDTO proveedorDTO){
		
		Map<String, Object> response = new HashMap<>();
	
        Proveedor proveedor = proveedorService.save(proveedorDTO.getProveedor());
        
        Set<Direccion> direccionList = proveedorDTO.getDireccion();
        List<Direccion> direccion = null;
	        
	        if(direccionList != null) {
	     	   for(Direccion direcciones: direccionList) {
	     		   direcciones.setProveedor(proveedor);
	     	   }
	     	   direccion = proveedorService.saveDireccion(direccionList);
	        }
        
        response.put("proveedor", proveedor);
        response.put("direccion", direccion);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }
	//UPDATE PROVEEDOR WITH DIRECCION
	 @PutMapping("/update/{id}")
	 public ResponseEntity<?> update(@PathVariable("id")long id,@RequestBody ProveedorDTO proveedorDTO){
		
		Map<String, Object> response = new HashMap<>();
					
		Proveedor proveedorUp = proveedorDTO.getProveedor();
		Proveedor proveedorUps = proveedorService.getOne(id);
		
			proveedorUps.setCodigo(proveedorUp.getCodigo());
			proveedorUps.setRuc(proveedorUp.getRuc());
			proveedorUps.setFechaInicio(proveedorUp.getFechaInicio());
			proveedorUps.setNombre(proveedorUp.getNombre());
			proveedorUps.setRubro(proveedorUp.getRubro());
			proveedorUps.setComentario(proveedorUp.getComentario());
			
	        Set<Direccion> direccionList = proveedorDTO.getDireccion();
	        List<Direccion> direccion = null;
		        
		        if(direccionList != null) {
		     	   for(Direccion direcciones: direccionList) {
		     		   direcciones.setProveedor(proveedorUps);
		     	   }
		     	   direccion = proveedorService.saveDireccion(direccionList);
		        }

        Proveedor proveedor = proveedorService.save(proveedorUps);
      
        response.put("proveedor", proveedor);
        response.put("direccion", direccion);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
 
	 }
	//DELETE PROVEEDOR 
	@DeleteMapping("/delete/{id}")
	    public ResponseEntity<?> delete(@PathVariable("id")Long id){
	        if(!proveedorService.existsById(id))
	            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
	        proveedorService.deleteProveedor(id);
	        return new ResponseEntity(new Mensaje("proveedor eliminado"), HttpStatus.OK);
	    }
}
