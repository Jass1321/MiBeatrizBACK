package com.app.repository.Maestro.Tercero;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Maestro.Tercero.Direccion;

@Repository
public interface DireccionRepository  extends JpaRepository<Direccion, Long>{

}
