package com.app.controller.Maestro.Banco;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.Maestro.Banco.BancoService;
import com.app.model.Maestro.Banco.Banco;

@RestController
@RequestMapping("/banco")
@CrossOrigin(origins = "http://localhost:4200")
public class BancoController {
	
	@Autowired
	BancoService bancoService;
	
	@GetMapping("/list")
	private ResponseEntity<List<Banco>> getAllBancos(){
		return ResponseEntity.ok(bancoService.findAll());
	}

}
