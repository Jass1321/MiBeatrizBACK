package com.app.controller.Maestro.Catalogo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Maestro.Catalogo.Familia;
import com.app.service.Maestro.Catalogo.FamiliaService;

@RestController
@RequestMapping("/familia")
@CrossOrigin(origins = "http://localhost:4200")
public class FamiliaController {

	@Autowired
	FamiliaService familiaService;
	
	@GetMapping("/list")
	public ResponseEntity<List<Familia>> list(){
		List<Familia> list = familiaService.findAll();
		return new ResponseEntity<List<Familia>>(list, HttpStatus.OK);
	}
	
}
