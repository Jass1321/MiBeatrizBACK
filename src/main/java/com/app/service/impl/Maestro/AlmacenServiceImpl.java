package com.app.service.impl.Maestro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.app.model.Maestro.Almacen;

@Repository
public interface AlmacenServiceImpl extends PagingAndSortingRepository<Almacen, Long> {

	public Page<Almacen> findAll(Pageable pageable);
	
}
