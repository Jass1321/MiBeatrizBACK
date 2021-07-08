package com.app.repository.Maestro;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.app.model.Maestro.Almacen;
import com.app.model.Maestro.Inventario;

@Repository
public interface InventarioRepository {

  //BASIC	
  public List<Inventario> findAll(Pageable pageable);
  public Inventario findById(Long id);
  public Inventario save(Inventario inventario);
	
  //EXTRA
  
  public List<Inventario> saveInventario(List<Inventario> inventario);
  public List<Inventario> findByInventarioId(Almacen almacenId);
  public boolean existDireccion(List<Inventario> inventarios, Long id);
	
}
