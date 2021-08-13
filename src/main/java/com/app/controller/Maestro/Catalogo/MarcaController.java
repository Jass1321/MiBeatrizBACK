package com.app.controller.Maestro.Catalogo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Maestro.Catalogo.Marca;
import com.app.service.Maestro.Catalogo.MarcaService;

@RestController
@RequestMapping("/marcas")
@CrossOrigin(origins = "http://localhost:4200")
public class MarcaController {
	
	@Autowired
	private MarcaService marcaService;
	
	/*----------READ----------*/
	@GetMapping("/listMarca")
	public ResponseEntity<List<Marca>> listMarca(){
		return ResponseEntity.ok(marcaService.list());
	}

	/*----------READ WITH PAGE----------*/
	@GetMapping("/listMarcaWithPage")
	public ResponseEntity<Page<Marca>> listMarcaWithPage(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10")int size,
			@RequestParam(defaultValue = "nombre") String order,
		    @RequestParam(defaultValue = "true") boolean asc
    ){
    	Page<Marca> marca = marcaService.listMarcaWithPage(
    			PageRequest.of(page,size, Sort.by(order))
		);
		if(!asc)
            marca = marcaService.listMarcaWithPage(
                    PageRequest.of(page, size, Sort.by(order).descending()));
		return new ResponseEntity<Page<Marca>>(marca, HttpStatus.OK);
	}
	
	/*----------DETAIL----------*/
	@GetMapping("/detail/{id}")
	public Marca getMarcasById(@PathVariable("id")Long id){
		return marcaService.getMarcaById(id);
	}
	
	/*----------CREATE----------*/
	@PostMapping("/create")
	public Marca createMarca(@Validated @RequestBody Marca marca) {
		return marcaService.save(marca);
	}
	
	/*----------UPDATE----------*/
	@PutMapping("/update/{id}")
	public Marca updateMarca(@PathVariable Long id, @Validated @RequestBody Marca marcaDTO) {
		return marcaService.update(id, marcaDTO) ;
	}
	
	/*----------DELETE----------*/
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteMarca(@PathVariable(value = "id") Long id){
		return marcaService.delete(id);
	}
}
