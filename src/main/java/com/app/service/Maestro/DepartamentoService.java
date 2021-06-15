package com.app.service.Maestro;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.dto.NotFoundException;
import com.app.model.Maestro.Organizacion.Area;
import com.app.model.Maestro.Organizacion.Departamento;
import com.app.repository.Maestro.Organizacion.AreaRepository;
import com.app.repository.Maestro.Organizacion.DepartamentoRepository;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private AreaRepository areaRepository;
	
	/*----------READ BASIC----------*/
	public Page<Area> listArea(Pageable pageable) {
		return areaRepository.findAll(pageable);
	}
	
	/*----------READ WITH PAGE----------*/
	public Page<Departamento> listDepWithPage(Pageable pageable){
		return  departamentoRepository.findAll(pageable);
	}
	
	public Page<Area> listAreaWithPage(Pageable pageable){
		return  areaRepository.findAll(pageable);
	}
	
	/*----------CONDITIONS----------*/
	public boolean existsById(Long id) {
		return departamentoRepository.existsById(id);
	}
	
	/*----------SEARCHS----------*/
	
	public Departamento getDepById(Long depId) {
		Departamento dep = new Departamento();
		dep = departamentoRepository.findById(depId)
				.orElseThrow(() -> new NotFoundException("Departamento ID - " + depId +"no existe"));
		return dep;
	}
	
	
	public Area getAreaIdByIdDep(Long areaId) {
		Area area = new Area(null, null);
		area = areaRepository.findById(areaId)
				.orElseThrow(() -> new NotFoundException("Area ID - " + areaId +"no existe"));
		return area;
	}
	/*----------CREATE----------*/
	public Departamento createDep(Departamento dep) {
		return departamentoRepository.save(dep);
	}
	/*----------UPDATE----------*/
	public Departamento updateDep(Long depId, Departamento depDTO) {
		Departamento dep = new Departamento();
		dep = departamentoRepository.findById(depId)
				.orElseThrow(() -> new NotFoundException("Departamento ID - " + depId +"no existe"));
		dep.setNombre(depDTO.getNombre());;
		
		Departamento depUp = departamentoRepository.save(dep);
		return depUp;
	}
	
	/*----------DELETE----------*/
	public ResponseEntity<?> deleteDep(Long depId){
		Departamento dep = new Departamento();
		dep = departamentoRepository.findById(depId)
				.orElseThrow(() -> new NotFoundException("Departamento ID - " + depId +"no existe"));
		
		departamentoRepository.delete(dep);
		return ResponseEntity.ok().build();
	}	
}
