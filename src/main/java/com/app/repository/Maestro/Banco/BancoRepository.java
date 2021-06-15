package com.app.repository.Maestro.Banco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Maestro.Banco.Banco;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long>{

}
