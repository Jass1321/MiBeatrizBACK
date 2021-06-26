package com.app.service.Maestro;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.dto.NotFoundException;
import com.app.model.Maestro.Banco;
import com.app.model.Maestro.CuentaBancaria;
import com.app.repository.Maestro.BancoRepository;
import com.app.repository.Maestro.CuentaBancariaRepository;

@Service
public class BancoService {

	@Autowired
	BancoRepository bancoRepository;

	@Autowired
	CuentaBancariaRepository cuentaBancariaRepository;

	/*----------READ BASIC----------*/
	public List<Banco> listBanco() {
		return bancoRepository.findAll();
	}
	
	public Page<CuentaBancaria> listCuenta(Pageable pageable) {
		return cuentaBancariaRepository.findAll(pageable);
	}

	/*----------READ WITH PAGE----------*/
	public Page<Banco> listBancopWithPage(Pageable pageable){
		return  bancoRepository.findAll(pageable);
	}
	
	public Page<CuentaBancaria> listCuentaWithPage(Pageable pageable){
		return  cuentaBancariaRepository.findAll(pageable);
	}
	
	/*----------CONDITIONS----------*/
	public boolean existsById(Long id) {
		return bancoRepository.existsById(id);
	}
	
	/*----------SEARCHS----------*/
	public Banco getBancoById(Long bancoId) {
		Banco banco = new Banco();
		banco = bancoRepository.findById(bancoId)
				.orElseThrow(() -> new NotFoundException("Banco ID - " + bancoId +"no existe"));
		return banco;
	}
	
	public Optional<CuentaBancaria> findById(Long id) {
		return null;
	}
	
	public CuentaBancaria getCuentaIdByIdBanco(Long cuentaId) {
		CuentaBancaria cuenta = new CuentaBancaria(null, null, null, null);
		cuenta = cuentaBancariaRepository.findById(cuentaId)
				.orElseThrow(() -> new NotFoundException("Cuenta Bancaria ID - " + cuentaId +"no existe"));
		return cuenta;
	}
	
	/*----------CREATE----------*/
	public Banco createeBanco(Banco banco) {
		return bancoRepository.save(banco);
	}
	
	public CuentaBancaria saveCuenta(CuentaBancaria cuenta) {
		return cuentaBancariaRepository.save(cuenta);
	}
	
	/*----------UPDATE----------*/
	public Banco updateBanco(Long bancoId, Banco bancoDTO) {
		Banco banco = new Banco();
		banco = bancoRepository.findById(bancoId)
				.orElseThrow(() -> new NotFoundException("Banco ID - " + bancoId +"no existe"));
		banco.setNombre(bancoDTO.getNombre());;
		
		Banco bancoUp = bancoRepository.save(banco);
		return bancoUp;
	}
	
	/*----------DELETE----------*/
	public ResponseEntity<?> deleteBanco(Long bancoId){
		Banco banco = new Banco();
		banco = bancoRepository.findById(bancoId)
				.orElseThrow(() -> new NotFoundException("Banco ID - " + bancoId +"no existe"));
		
		bancoRepository.delete(banco);
		return ResponseEntity.ok().build();
	}

	public void deleteByCuentaId(Long id) {
		cuentaBancariaRepository.deleteById(id);
	}

	public void deleteCuenta(CuentaBancaria cuenta) {
		cuentaBancariaRepository.delete(cuenta);
	}	
}
