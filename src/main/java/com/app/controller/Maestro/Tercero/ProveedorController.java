package com.app.controller.Maestro.Tercero;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.dto.Mensaje;
import com.app.dto.ProveedorDTO;
import com.app.model.Maestro.Tercero.Proveedor;
import com.app.service.Maestro.Tercero.ProveedorService;

@RestController
@RequestMapping("/proveedor")
@CrossOrigin(origins = "http://localhost:4200")
public class ProveedorController {
	
	@Autowired
	ProveedorService proveedorService;
	
	@GetMapping("/lista")
	public ResponseEntity<List<Proveedor>> list(){
		List<Proveedor> list = proveedorService.list();
		return new ResponseEntity(list, HttpStatus.OK);
	}
	
	@GetMapping("/detail/{id}")
	public ResponseEntity<Proveedor> getById(@PathVariable("id") int id){
		if(!proveedorService.existsById(id))
			return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
		Proveedor proveedor = proveedorService.getOne(id).get();
		return new ResponseEntity(proveedor, HttpStatus.OK);
	}
	
	
	@GetMapping("/detailname/{ruc}")
    public ResponseEntity<Proveedor> getByRuc(@PathVariable("ruc") int ruc){
        if(!proveedorService.existsByRuc(ruc))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Proveedor proveedor = proveedorService.getByRuc(ruc).get();
        return new ResponseEntity(proveedor, HttpStatus.OK);
    }
	
	@PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProveedorDTO proveedorDTO){
		
        if(proveedorService.existsByRuc(proveedorDTO.getRuc()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Proveedor proveedor = new Proveedor(
        		proveedorDTO.getCodigo(),
        		proveedorDTO.getRuc(), 
        		proveedorDTO.getRazonSocial(), 
        		proveedorDTO.getFechaInicio(),
        		proveedorDTO.getRubroActividad(),
        		proveedorDTO.getDireccion(), 
        		proveedorDTO.getTelefono(),
        		proveedorDTO.getCorreo()
        		);
        proveedorService.save(proveedor);
        return new ResponseEntity(new Mensaje("proveedor creado"), HttpStatus.OK);
    }
	
	 @PutMapping("/update/{id}")
	 public ResponseEntity<?> update(@PathVariable("id")int id,@RequestBody ProveedorDTO proveedorDTO){
		 if(!proveedorService.existsById(id))
	            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
	        if(proveedorService.existsByRuc(proveedorDTO.getRuc()) && proveedorService.getByRuc(proveedorDTO.getRuc()).get().getId() != id)
	            return new ResponseEntity(new Mensaje("ese ruc ya existe"), HttpStatus.BAD_REQUEST);
	         
	        Proveedor proveedor = proveedorService.getOne(id).get();
	        proveedor.setCodigo(proveedorDTO.getCodigo());
	        proveedor.setRuc(proveedorDTO.getRuc());
	        proveedor.setRazonSocial(proveedorDTO.getRazonSocial());
	        proveedor.setFechaInicio(proveedorDTO.getFechaInicio());
	        proveedor.setRubroActividad(proveedorDTO.getRubroActividad());
	        
	        proveedor.setDireccion(proveedorDTO.getDireccion());
	        proveedor.setTelefono(proveedorDTO.getTelefono());
	        proveedor.setCorreo(proveedorDTO.getCorreo());
	        proveedorService.save(proveedor);
	        return new ResponseEntity(new Mensaje("proveedor actualizado"), HttpStatus.OK);
	 
	 }
	 
	 @DeleteMapping("/delete/{id}")
	    public ResponseEntity<?> delete(@PathVariable("id")int id){
	        if(!proveedorService.existsById(id))
	            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
	        proveedorService.delete(id);
	        return new ResponseEntity(new Mensaje("proveedor eliminado"), HttpStatus.OK);
	    }
	

}
