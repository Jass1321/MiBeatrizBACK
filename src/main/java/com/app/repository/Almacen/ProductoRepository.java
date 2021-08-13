package com.app.repository.Almacen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.app.model.Inventario.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> ,
	JpaSpecificationExecutor<Producto>{
	
	
}
