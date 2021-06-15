package com.app.service.Maestro.Banco;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.model.Maestro.Banco.Banco;
import com.app.repository.Maestro.Banco.BancoRepository;

@Service
public class BancoService {

	@Autowired
	BancoRepository bancoRepository;

	
	public List<Banco> findAll() {
		return bancoRepository.findAll();
	}

	
	
	public <S extends Banco> S save(S entity) {
		// TODO Auto-generated method stub
		return bancoRepository.save(entity);
	}



	
}
