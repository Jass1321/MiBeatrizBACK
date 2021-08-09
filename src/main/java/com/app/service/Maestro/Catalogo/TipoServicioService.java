package com.app.service.Maestro.Catalogo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.dto.NotFoundException;
import com.app.model.Maestro.Catalogo.TipoServicio;
import com.app.repository.Maestro.Catalogo.TipoServicioRepository;

@Service
@Transactional
public class TipoServicioService {
	
	@Autowired
	TipoServicioRepository tipoRepository;

	/*----------CONDITIONS----------*/
	public boolean existsById(Long id) {
		return tipoRepository.existsById(id);
	}
	
	/*----------SEARCHS----------*/
	public TipoServicio getById(Long id) {
		TipoServicio x = new TipoServicio();
		x = tipoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ID - " + id +"no existe"));
		return x;
	}
	
	/*----------READ BASIC----------*/
	public List<TipoServicio> list(){
		return tipoRepository.findAll();
	}
	
	/*----------READ WITH PAGE----------*/
	public Page<TipoServicio> listWithPage(Pageable pageable){
		return  tipoRepository.findAll(pageable);
	}
	
	/*----------CREATE----------*/
	public TipoServicio create(TipoServicio tipo) {
		return tipoRepository.save(tipo);
	}
	
	/*----------DELETE----------*/
	public ResponseEntity<?> delete(Long id){
		TipoServicio x = new TipoServicio();
		x = tipoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ID - " + id +"no existe"));
		tipoRepository.delete(x);
		return ResponseEntity.ok().build();
	}	
}
