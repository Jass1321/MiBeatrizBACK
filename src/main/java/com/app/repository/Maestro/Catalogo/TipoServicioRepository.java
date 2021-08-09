package com.app.repository.Maestro.Catalogo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Maestro.Catalogo.TipoServicio;

@Repository
public interface TipoServicioRepository  extends JpaRepository<TipoServicio, Long>  {

}
