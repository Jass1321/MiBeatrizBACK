package com.app.service.Maestro.Catalogo;

import com.app.model.Maestro.Catalogo.Familia;
import com.app.repository.Maestro.Catalogo.FamiliaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
//readOnly = true solo para lectura no CRUD
@Transactional(readOnly = true)
public class FamiliaService {

	@Autowired
	FamiliaRepository familiaRepository;
	
	public List<Familia> findAll(){
		return familiaRepository.findAll();
	}
}
