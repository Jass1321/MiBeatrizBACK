package com.app.repository.Maestro.Organizacion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Maestro.Organizacion.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

}
