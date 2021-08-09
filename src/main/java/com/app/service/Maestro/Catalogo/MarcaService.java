package com.app.service.Maestro.Catalogo;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.dto.NotFoundException;
import com.app.model.Maestro.Catalogo.Marca;
import com.app.repository.Maestro.Catalogo.MarcaRepository;

@Service
@Transactional
public class MarcaService {
	
	@Autowired
	MarcaRepository marcaRepository;
	
	/*----------CONDITIONS----------*/
	public boolean existsById(Long id) {
		return marcaRepository.existsById(id);
	}
	
	public boolean existsByNombre(String nombre) {
		return marcaRepository.existsByNombre(nombre);
	}
	
	/*----------SEARCHS----------*/
	public Marca getMarcaById(Long marcaId) {
		Marca mar = new Marca();
		mar = marcaRepository.findById(marcaId)
				.orElseThrow(() -> new NotFoundException("Marca ID - " + marcaId +"no existe"));
		return mar;
	}
	
	public Optional<Marca> getById(Long id) {
		return marcaRepository.findById(id);
	}
	
	public Optional<Marca> getByNombre(String nombre) {
		return marcaRepository.findByNombre(nombre);
	}
	
	
	/*----------READ BASIC----------*/
	public List<Marca> list() {
		return marcaRepository.findAll();
	}
	
	/*----------READ WITH PAGE----------*/
	public Page<Marca> listMarcaWithPage(Pageable pageable){
		return  marcaRepository.findAll(pageable);
	}
	
	
	/*----------CREATE----------*/
	public Marca save(Marca marca) {
		return marcaRepository.save(marca);
	}
	
	/*----------UPDATE----------*/
	public Marca update(Long marcaId, Marca marcaDTO) {
		Marca marca = new Marca();
		marca = marcaRepository.findById(marcaId)
				.orElseThrow(() -> new NotFoundException("Marca ID - " + marcaId +"no existe"));
		marca.setNombre(marcaDTO.getNombre());;
		
		Marca marcaUp = marcaRepository.save(marca);
		return marcaUp;
	}
	
	/*----------DELETE----------*/
	public ResponseEntity<?> delete(Long marcaId) {
		Marca marca = new Marca();
		marca = marcaRepository.findById(marcaId)
				.orElseThrow(() -> new NotFoundException("Marca ID - " + marcaId +"no existe"));
		
		marcaRepository.delete(marca);
		return ResponseEntity.ok().build();
	}
	
}
