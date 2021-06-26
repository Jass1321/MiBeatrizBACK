package com.app.repository.Maestro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Maestro.Banco;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long>{

}
