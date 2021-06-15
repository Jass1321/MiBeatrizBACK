package com.app.controller.Maestro.Organizacion;

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

import com.app.dto.NotFoundException;
import com.app.model.Maestro.Organizacion.Area;
import com.app.model.Maestro.Organizacion.Departamento;
import com.app.repository.Maestro.Organizacion.AreaRepository;
import com.app.repository.Maestro.Organizacion.DepartamentoRepository;
import com.app.service.Maestro.DepartamentoService;

@RestController
@RequestMapping("/departamentos")
@CrossOrigin(origins = "http://localhost:4200")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService departamentoService;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private AreaRepository areaRepository;
	
	/*----------READ----------*/
	//READ AREA WITH PAGE
	@GetMapping("/listArea")
	public ResponseEntity<Page<Area>> listAllArea(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10")int size,
			@RequestParam(defaultValue = "nombre") String order,
		    @RequestParam(defaultValue = "true") boolean asc
    ){
    	Page<Area> area = departamentoService.listAreaWithPage(
    			PageRequest.of(page,size, Sort.by(order))
		);
		if(!asc)
            area = departamentoService.listAreaWithPage(
                    PageRequest.of(page, size, Sort.by(order).descending()));
		return new ResponseEntity<Page<Area>>(area, HttpStatus.OK);
	}
	//READ DEP WITH PAGE
	@GetMapping("/listDepa")
	public ResponseEntity<Page<Departamento>> listAllDepa(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10")int size,
			@RequestParam(defaultValue = "nombre") String order,
		    @RequestParam(defaultValue = "true") boolean asc
    ){
    	Page<Departamento> dep = departamentoService.listDepWithPage(
    			PageRequest.of(page,size, Sort.by(order))
		);
		if(!asc)
            dep = departamentoService.listDepWithPage(
                    PageRequest.of(page, size, Sort.by(order).descending()));
		return new ResponseEntity<Page<Departamento>>(dep, HttpStatus.OK);
	}
	//READ DETAIL BY ID
	@GetMapping("/listDepa/{depId}")
	public Departamento getDepById(@PathVariable("depId") Long depId){
		return departamentoService.getDepById(depId);
	}
	//READ AREAS BY DEP ID 
	@GetMapping("/listDepa/{depId}/areas")
	public List<Area> getAreasByDepId(
			@PathVariable("depId") Long depId){
		return areaRepository.findByDepId(depId);
		
	}
	@GetMapping("/listDepa/{depId}/areas/{areaId}")
	public Area getAreaIdByDepId(
			@PathVariable(value = "depId") Long depId,
			@PathVariable(value = "areaId")Long areaId){
		return departamentoService.getAreaIdByIdDep(areaId);
		
	}
	
	/*----------CREATE----------*/
	@PostMapping("/create")
	public Departamento createDepartamento(@Validated @RequestBody Departamento dep) {
		return departamentoService.createDep(dep);
	}
	
	@PostMapping("/create/{depId}/areas")
	public Area createArea(@PathVariable(value = "depId") Long depId, @Validated @RequestBody Area area) {
		return departamentoRepository.findById(depId).map(
				dep -> {
						area.setDep(dep);
						return areaRepository.save(area);
				}).orElseThrow(() -> new NotFoundException("Departamento ID " + depId + " not found"));
	}
	
	/*----------UPDATE----------*/
	@PutMapping("/update/{depId}")
	public Departamento updateDep(@PathVariable Long depId, @Validated @RequestBody Departamento depDTO) {
		return departamentoService.updateDep(depId, depDTO) ;
	}
	
	@PutMapping("/update/{depId}/areas/{areaId}")
	public Area updateArea(
			@PathVariable(value = "depId") Long depId,
			@PathVariable(value = "areaId")Long areaId, 
			@Validated @RequestBody Area areaDTO) {
		
		if (!departamentoRepository.existsById(depId)) {
			throw new NotFoundException("Departamento ID " + depId + " not found");
		}
		return areaRepository.findById(areaId).map(
				area -> {
						area.setNombre(areaDTO.getNombre());
						return areaRepository.save(area);
				}).orElseThrow(() -> new NotFoundException("Area ID " + areaId + "not found"));
	}
	
	/*----------DELETE----------*/
	@DeleteMapping("/delete/{depId}")
	public ResponseEntity<?> deleteDep(@PathVariable(value = "depId") Long depId){
		return departamentoService.deleteDep(depId);
	}
	
	@DeleteMapping("/delete/{depId}/areas/{areaId}")
	public ResponseEntity<?> deleteArea(
			@PathVariable(value = "depId") Long depId,
			@PathVariable(value = "areaId") Long areaId) {
		return areaRepository.findByIdAndDepId(areaId, depId).map(
				area -> {
						areaRepository.delete(area);
						return ResponseEntity.ok().build();
					}).orElseThrow(() -> new NotFoundException(
							"Area not found with ID " + areaId + " and departamento ID  " + depId));
				}
}
