package com.app.service.Maestro.Catalogo;

import com.app.dto.NotFoundException;
import com.app.model.Maestro.Catalogo.Familia;
import com.app.repository.Maestro.Catalogo.FamiliaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

import javax.transaction.Transactional;

@Service
@Transactional
public class FamiliaService {

	@Autowired
	FamiliaRepository familiaRepository;
	
	/*----------CONDITIONS----------*/
	public boolean existsById(Long id) {
		return familiaRepository.existsById(id);
	}
	
	/*----------SEARCHS----------*/
	public Familia getFamiliaById(Long id) {
		Familia x = new Familia();
		x = familiaRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ID - " + id +"no existe"));
		return x;
	}
	
	/*----------READ BASIC----------*/
	public List<Familia> listFamilia(){
		return familiaRepository.findAll();
	}
	
	/*----------READ WITH PAGE----------*/
	public Page<Familia> listFamiliaWithPage(Pageable pageable){
		return  familiaRepository.findAll(pageable);
	}
	
	/*----------CREATE----------*/
	public Familia createFam(Familia fam) {
		return familiaRepository.save(fam);
	}
	
	/*----------DELETE----------*/
	public ResponseEntity<?> deleteFam(Long id){
		Familia x = new Familia();
		x = familiaRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("ID - " + id +"no existe"));
		
		familiaRepository.delete(x);
		return ResponseEntity.ok().build();
	}	
}
