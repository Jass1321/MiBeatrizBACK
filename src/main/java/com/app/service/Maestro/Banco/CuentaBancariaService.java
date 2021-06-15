package com.app.service.Maestro.Banco;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.model.Maestro.Banco.CuentaBancaria;
import com.app.repository.Maestro.Banco.BancoRepository;
import com.app.repository.Maestro.Banco.CuentaBancariaRepository;

@Service
public class CuentaBancariaService {

	@Autowired
	CuentaBancariaRepository cuentaBancariaRepository;

	public List<CuentaBancaria> findAll() {
		// TODO Auto-generated method stub
		return cuentaBancariaRepository.findAll();
	}

	public <S extends CuentaBancaria> S save(S entity) {
		// TODO Auto-generated method stub
		return cuentaBancariaRepository.save(entity);
	}

	
	public Optional<CuentaBancaria> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}


	public void deleteById(Long id) {
		cuentaBancariaRepository.deleteById(id);
	}

	public void delete(CuentaBancaria entity) {
		cuentaBancariaRepository.delete(entity);
	}

}
