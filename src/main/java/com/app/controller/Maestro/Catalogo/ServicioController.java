package com.app.controller.Maestro.Catalogo;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.NotFoundException;
import com.app.model.Maestro.Catalogo.Servicio;
import com.app.model.Maestro.Catalogo.TipoServicio;
import com.app.repository.Maestro.Catalogo.ServicioRepository;
import com.app.repository.Maestro.Catalogo.TipoServicioRepository;
import com.app.service.Maestro.Catalogo.ServicioService;
import com.app.service.Maestro.Catalogo.TipoServicioService;

@RestController
@RequestMapping("/servicios")
@CrossOrigin(origins = "http://localhost:4200")
public class ServicioController {

	@Autowired
	TipoServicioService tipoService;
	
	@Autowired
	ServicioService servicioService;
	
	@Autowired
	TipoServicioRepository tipoRepository;
	
	@Autowired
	ServicioRepository servicioRepository;
	
	/*----------READ BASIC----------*/
	@GetMapping("/listTipo")
	private ResponseEntity<List<TipoServicio>> listTipo() {
		return ResponseEntity.ok(tipoService.list());
	}
	
	@GetMapping("/listServicio")
	private ResponseEntity<List<Servicio>> listServicio() {
		return ResponseEntity.ok(servicioService.list());
	}
	
	/*----------READ WITH PAGE----------*/
	@GetMapping("/listTipoWithPage")
	public ResponseEntity<Page<TipoServicio>> listTipoWithPage(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, 
			@RequestParam(defaultValue = "nombre") String order,
			@RequestParam(defaultValue = "true") boolean asc) {
		
		Page<TipoServicio> t = tipoService.listWithPage(PageRequest.of(page, size, Sort.by(order)));
		if (!asc)
			t = tipoService.listWithPage(PageRequest.of(page, size, Sort.by(order).descending()));
		return new ResponseEntity<Page<TipoServicio>>(t, HttpStatus.OK);
	}
	
	@GetMapping("/listServicioWithPage")
	public ResponseEntity<Page<Servicio>> listServicioWithPage(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, 
			@RequestParam(defaultValue = "nombre") String order,
			@RequestParam(defaultValue = "true") boolean asc) {
		
		Page<Servicio> s = servicioService.listWithPage(PageRequest.of(page, size, Sort.by(order)));
		if (!asc)
			s = servicioService.listWithPage(PageRequest.of(page, size, Sort.by(order).descending()));
		return new ResponseEntity<Page<Servicio>>(s, HttpStatus.OK);
	}
	
	/*----------DETAIL----------*/
	@GetMapping("/tipo/{tipoId}")
	public TipoServicio getTipoById(@PathVariable("tipoId") Long tipoId) {
		return tipoService.getById(tipoId);
	}
	
	@GetMapping("/tipo/{tipoId}/servicios")
	public List<Servicio> getByTipoId(@PathVariable("tipoId") Long famId) {
		return servicioRepository.findByTipoId(famId);
	}

	@GetMapping("/tipo/{tipoId}/servicios/{servId}")
	public Servicio getServicioById(
			@PathVariable(value = "tipoId") Long tipoId,
			@PathVariable(value = "servId") Long servId) {
		return servicioService.getById(servId);
	}
	
	/*----------CREATE----------*/
	@PostMapping("/create")
	public TipoServicio createTipo(@Validated @RequestBody TipoServicio tipo) {
		return tipoService.create(tipo);
	}
	
	@PostMapping("/create/{tipoId}/servicios")
	public Servicio createServicio(
			@PathVariable(value = "tipoId") Long tipoId,
			@Validated @RequestBody Servicio serv) {
		return tipoRepository.findById(tipoId).map(tipo -> {
			serv.setTipoServicio(tipo);
			return servicioRepository.save(serv);
		}).orElseThrow(() -> new NotFoundException("ID " + tipoId + " not found"));
	}

	@PostMapping("/save/servicios")
	private ResponseEntity<Servicio> saveSubfamilia(@RequestBody Servicio s) {
		try {
			Servicio x = servicioRepository.save(s);
			return ResponseEntity.created(new URI("/servicios" + x.getId())).body(x);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/*----------DELETE----------*/
	@DeleteMapping("/delete/{tipoId}")
	public ResponseEntity<?> deleteTipo(@PathVariable(value = "tipoId") Long tipoId) {
		return tipoService.delete(tipoId);
	}
	
	@DeleteMapping("/delete/{tipoId}/servicios/{servId}")
	public ResponseEntity<?> deleteServicio(
			@PathVariable(value = "tipoId") Long tipoId,
			@PathVariable(value = "servId") Long servId) {
		return servicioRepository.findByIdAndTipoId(servId, tipoId).map(sub -> {
			servicioRepository.delete(sub);
			return ResponseEntity.ok().build();
		}).orElseThrow(
				() -> new NotFoundException("Servicio not found with ID " + servId + " and Tipo ID  " + tipoId));
	}

	@DeleteMapping("/delete/servicios/{id}")
	private ResponseEntity<Boolean> removeServicio(@PathVariable("id") Long id) {
		servicioService.delete(id);
		return ResponseEntity.ok(!(servicioService.findById(id) != null));
	}
	

}
