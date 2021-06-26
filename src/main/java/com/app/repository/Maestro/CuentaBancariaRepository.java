package com.app.repository.Maestro;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Maestro.CuentaBancaria;

@Repository
public interface CuentaBancariaRepository extends JpaRepository<CuentaBancaria, Long>{

	List<CuentaBancaria> findByBancoId(Long bancoId);
	Optional<CuentaBancaria> findByIdAndBancoId(Long id, Long bancoId);
}
