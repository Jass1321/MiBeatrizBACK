package com.app.repository.Maestro.Tercero;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Maestro.Tercero.CuentaTercero;

@Repository
public interface CuentaRepository  extends JpaRepository<CuentaTercero, Integer> {

}
