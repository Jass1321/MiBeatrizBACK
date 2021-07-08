package com.app.service.impl.Maestro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.app.model.Maestro.Almacen;
import com.app.model.Maestro.Inventario;



public interface InventarioServiceImpl extends JpaRepository<Inventario, Long> {

public List<Inventario> findByAlmacenId(Almacen almacenId);
	
}
