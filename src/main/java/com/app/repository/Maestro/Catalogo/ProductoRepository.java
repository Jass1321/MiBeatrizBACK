package com.app.repository.Maestro.Catalogo;

import com.app.model.Maestro.Catalogo.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductoRepository extends JpaRepository<Producto, Long> ,
	JpaSpecificationExecutor<Producto>{
}
