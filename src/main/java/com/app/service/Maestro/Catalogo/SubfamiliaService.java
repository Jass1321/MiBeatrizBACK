package com.app.service.Maestro.Catalogo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.app.dto.NotFoundException;
import com.app.model.Maestro.Catalogo.Subfamilia;
import com.app.repository.Maestro.Catalogo.SubfamiliaRepository;

@Service
@Transactional
public class SubfamiliaService {

	@Autowired
	SubfamiliaRepository subfamiliaRepository;
	
	/*----------SEARCHS----------*/
	public Subfamilia getSubIdByIdFamId(Long subId) {
		Subfamilia subfamilia = new Subfamilia(null, null);
		subfamilia = subfamiliaRepository.findById(subId)
				.orElseThrow(() -> new NotFoundException("Area ID - " + subId +"no existe"));
		return subfamilia;
	}
	
	public Optional<Subfamilia> findById(Long id) {
		return null;
	}
	
	/*----------READ BASIC----------*/
	public Page<Subfamilia> listSubfamilia(Pageable pageable) {
		return subfamiliaRepository.findAll(pageable);
	}
	
	/*----------READ WITH PAGE----------*/
	public Page<Subfamilia> listSubfamiliaWithPage(Pageable pageable){
		return  subfamiliaRepository.findAll(pageable);
	}
	/*----------CREATE----------*/
	public Subfamilia  saveSub(Subfamilia subfamilia) {
		return subfamiliaRepository.save(subfamilia);
	}
	
	/*----------DELETE----------*/
	public void deleteBySubId(Long id) {
		subfamiliaRepository.deleteById(id);
	}
}
