package com.app.repository.Almacen;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Inventario.ProductoImage;

public interface ProductoImageRepository extends JpaRepository<ProductoImage, Long>  {
	Optional<ProductoImage> findByName(String name);

}
