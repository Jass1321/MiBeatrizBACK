package com.app.service.Maestro.Catalogo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.dto.NotFoundException;
import com.app.model.Maestro.Catalogo.Servicio;
import com.app.repository.Maestro.Catalogo.ServicioRepository;

@Service
@Transactional
public class ServicioService {
	
	@Autowired
	ServicioRepository servicioRepository;
	
	/*----------SEARCHS----------*/
	public Servicio getById(Long id) {
		Servicio x = new Servicio(null, null);
		x = servicioRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ID - " + id +"no existe"));
		return x;
	}
	
	public Optional<Servicio> findById(Long id) {
		return null;
	}
	
	/*----------READ BASIC----------*/
	public List<Servicio> list(){
		return servicioRepository.findAll();
	}
	
	/*----------READ WITH PAGE----------*/
	public Page<Servicio> listWithPage(Pageable pageable){
		return  servicioRepository.findAll(pageable);
	}
	/*----------CREATE----------*/
	public Servicio  create(Servicio s) {
		return servicioRepository.save(s);
	}
	
	/*----------DELETE----------*/
	public void delete(Long id) {
		servicioRepository.deleteById(id);
	}
}
