package com.app.controller.Maestro.Banco;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Maestro.Banco.CuentaBancaria;
import com.app.service.Maestro.Banco.CuentaBancariaService;

@RestController
@RequestMapping("/cuentaBancaria")
@CrossOrigin(origins = "http://localhost:4200")
public class CuentaBancariaController {

	@Autowired
	CuentaBancariaService cuentaBancariaService;
	
	@GetMapping("/list")
	private ResponseEntity<List<CuentaBancaria>> getAllCuentasBancarias(){
		return ResponseEntity.ok(cuentaBancariaService.findAll());
	}
	
	@PostMapping("/create")
	private ResponseEntity<CuentaBancaria> saveCuentaBancaria(@RequestBody CuentaBancaria cuenta){
		try {
			CuentaBancaria cuentaGuardada = cuentaBancariaService.save(cuenta);		
		return ResponseEntity.created(new URI("/cuentaBancaria"+cuentaGuardada.getId())).body(cuentaGuardada);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}	
	}
	
	//DELETE  
	@DeleteMapping("/delete/{id}")
	private ResponseEntity<Boolean> deleteCuentaBancaria (@PathVariable("id")Long id){
		cuentaBancariaService.deleteById(id);
		return ResponseEntity.ok(!(cuentaBancariaService.findById(id)!=null));
		
	}
	
}
