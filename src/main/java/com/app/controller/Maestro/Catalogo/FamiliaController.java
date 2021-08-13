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
import com.app.model.Maestro.Catalogo.Familia;
import com.app.model.Maestro.Catalogo.Subfamilia;
import com.app.repository.Maestro.Catalogo.FamiliaRepository;
import com.app.repository.Maestro.Catalogo.SubfamiliaRepository;
import com.app.service.Maestro.Catalogo.FamiliaService;
import com.app.service.Maestro.Catalogo.SubfamiliaService;

@RestController
@RequestMapping("/familias")
@CrossOrigin(origins = "http://localhost:4200")
public class FamiliaController {

	@Autowired
	FamiliaService familiaService;

	@Autowired
	FamiliaRepository familiaRepository;

	@Autowired
	SubfamiliaService subfamiliaService;

	@Autowired
	SubfamiliaRepository subfamiliaRepository;

	/*----------READ----------*/
	@GetMapping("/listFam")
	private ResponseEntity<List<Familia>> listFamilia() {
		return ResponseEntity.ok(familiaService.listFamilia());
	}
	
	@GetMapping("/listSub")
	private ResponseEntity<List<Subfamilia>> listSubfamilia() {
		return ResponseEntity.ok(subfamiliaService.listSubfamilia());
	}
	/*----------READ WITH PAGE----------*/
	// READ FAMILIA WITH PAGE
	@GetMapping("/listFamilia")
	public ResponseEntity<Page<Familia>> listAllFamilia(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, 
			@RequestParam(defaultValue = "nombre") String order,
			@RequestParam(defaultValue = "true") boolean asc) {
		
		Page<Familia> f = familiaService.listFamiliaWithPage(PageRequest.of(page, size, Sort.by(order)));
		if (!asc)
			f = familiaService.listFamiliaWithPage(PageRequest.of(page, size, Sort.by(order).descending()));
		return new ResponseEntity<Page<Familia>>(f, HttpStatus.OK);
	}

	// READ SUB-FAMILIA WITH PAGE
	@GetMapping("/listSubfamilia")
	public ResponseEntity<Page<Subfamilia>> listAllSub(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "nombre") String order,
			@RequestParam(defaultValue = "true") boolean asc) {
		Page<Subfamilia> sub = subfamiliaService.listSubfamiliaWithPage(PageRequest.of(page, size, Sort.by(order)));
		if (!asc)
			sub = subfamiliaService.listSubfamiliaWithPage(PageRequest.of(page, size, Sort.by(order).descending()));
		return new ResponseEntity<Page<Subfamilia>>(sub, HttpStatus.OK);
	}

	// READ DETAIL BY ID
	@GetMapping("/listFamilia/{famId}")
	public Familia getFamById(@PathVariable("famId") Long famId) {
		return familiaService.getFamiliaById(famId);
	}

	// READ AREAS BY DEP ID
	@GetMapping("/listFamilia/{famId}/subfamilias")
	public List<Subfamilia> getSubByFamId(@PathVariable("famId") Long famId) {
		return subfamiliaRepository.findByFamiliaId(famId);
	}

	@GetMapping("/listFamilia/{famId}/subfamilias/{subId}")
	public Subfamilia getSubIdByFamId(@PathVariable(value = "famId") Long famId,
			@PathVariable(value = "subId") Long subId) {
		return subfamiliaService.getSubIdByIdFamId(subId);

	}

	/*----------CREATE----------*/
	@PostMapping("/create")
	public Familia createFam(@Validated @RequestBody Familia fam) {
		return familiaService.createFam(fam);
	}

	@PostMapping("/create/{famId}/subfamilias")
	public Subfamilia createSub(@PathVariable(value = "famId") Long famId,
			@Validated @RequestBody Subfamilia subfamilia) {
		return familiaRepository.findById(famId).map(fam -> {
			subfamilia.setFamilia(fam);
			return subfamiliaRepository.save(subfamilia);
		}).orElseThrow(() -> new NotFoundException("Familia ID " + famId + " not found"));
	}

	@PostMapping("/save/subfamilias")
	private ResponseEntity<Subfamilia> saveSubfamilia(@RequestBody Subfamilia subfamilia) {
		try {
			Subfamilia subGuardada = subfamiliaService.saveSub(subfamilia);
			return ResponseEntity.created(new URI("/subfamilias" + subGuardada.getId())).body(subGuardada);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/*----------DELETE----------*/
	@DeleteMapping("/delete/{famId}")
	public ResponseEntity<?> deleteFam(@PathVariable(value = "famId") Long famId) {
		return familiaService.deleteFam(famId);
	}

	@DeleteMapping("/delete/{famId}/subfamilias/{subId}")
	public ResponseEntity<?> deleteSubfamilia(@PathVariable(value = "famId") Long famId,
			@PathVariable(value = "subId") Long subId) {
		return subfamiliaRepository.findByIdAndFamiliaId(subId, famId).map(sub -> {
			subfamiliaRepository.delete(sub);
			return ResponseEntity.ok().build();
		}).orElseThrow(
				() -> new NotFoundException("Subfamilia not found with ID " + subId + " and familia ID  " + famId));
	}

	@DeleteMapping("/delete/subfamilias/{id}")
	private ResponseEntity<Boolean> deleteArea(@PathVariable("id") Long id) {
		subfamiliaService.deleteBySubId(id);
		return ResponseEntity.ok(!(subfamiliaService.findById(id) != null));

	}

}
