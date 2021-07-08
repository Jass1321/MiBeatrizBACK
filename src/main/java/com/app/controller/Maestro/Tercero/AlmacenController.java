package com.app.controller.Maestro.Tercero;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.Maestro.Tercero.AlmacenDTO;
import com.app.model.Maestro.Almacen;
import com.app.model.Maestro.Inventario;
import com.app.repository.Maestro.InventarioRepository;
import com.app.service.Maestro.Tercero.AlmacenService;



@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/almacen")
public class AlmacenController {

	@Autowired
	private AlmacenService almacenService;
	
	@Autowired
	private InventarioRepository inventarioService;
	
	@GetMapping("/list/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		Almacen almacen = almacenService.findById(id);
		List<Inventario> inventarios = inventarioService.findByInventarioId(almacen);
		
		response.put("almacen", almacen);
		response.put("inventario", inventarios);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create( @RequestBody AlmacenDTO almacenDTO) {
		
		Map<String, Object> response = new HashMap<>();
		Almacen almacenNew = almacenService.save(almacenDTO.getAlmacen());
		
		List<Inventario> inventarios = almacenDTO.getInventario();
		List<Inventario> inventarioNew = null;
		if(inventarios != null) {
			for(Inventario inventario : inventarios) {
				inventario.setAlmacenId(almacenNew);
			}
			inventarioNew = inventarioService.saveInventario(inventarios);
		}
	
		response.put("alamacen", almacenNew);
		response.put("inventario", inventarioNew);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
}
