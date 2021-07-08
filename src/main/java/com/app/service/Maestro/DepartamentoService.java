package com.app.service.Maestro;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.dto.NotFoundException;
import com.app.model.Maestro.Area;
import com.app.model.Maestro.Departamento;
import com.app.repository.Maestro.AreaRepository;
import com.app.repository.Maestro.DepartamentoRepository;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private AreaRepository areaRepository;
	
	/*----------READ BASIC----------*/
	public List<Departamento> listDep(){
		return departamentoRepository.findAll();
	}
	
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
	
	public Optional<Area> findById(Long id) {
		return null;
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
	
	public Area  saveArea(Area area) {
		return areaRepository.save(area);
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
	
	public void deleteByAreaId(Long id) {
		areaRepository.deleteById(id);
	}
}
