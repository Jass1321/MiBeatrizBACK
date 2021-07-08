package com.app.service.Maestro.Tercero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.model.Maestro.Almacen;
import com.app.service.impl.Maestro.AlmacenServiceImpl;

@Service
public class AlmacenService {

	@Autowired
	private AlmacenServiceImpl almacenServiceImpl;
	
	
	
	public Almacen findById(Long id) {
		// TODO Auto-generated method stub
		return almacenServiceImpl.findById(id).orElse(null);
	}

	public Almacen save(Almacen almacen) {
		// TODO Auto-generated method stub
		return almacenServiceImpl.save(almacen);
	}

}
