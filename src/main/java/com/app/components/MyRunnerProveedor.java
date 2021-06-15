package com.app.components;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.app.model.Maestro.Tercero.Direccion;
import com.app.model.Maestro.Tercero.Proveedor;
import com.app.repository.Maestro.Tercero.DireccionRepository;
import com.app.repository.Maestro.Tercero.ProveedorRepository;

@Component
public class MyRunnerProveedor implements CommandLineRunner {

@Override
	public void run(String... args) throws Exception {
	/*
	 * 	@Autowired
	DireccionRepository direccionRepository;
	
	@Autowired
	ProveedorRepository proveedorRepository;
	
	
		Proveedor proveedor01 = new Proveedor("PROV-02", 0, null, null, null, null, null);
		Proveedor proveedor02 = new Proveedor("PROV-03", 0, null, null, null, null, null);
	  
	  Direccion dirA = new Direccion();
	  Direccion dirB = new Direccion();
	  Direccion dirC = new Direccion();
	 
	 dirA.setProveedor(proveedor01);
	 dirB.setProveedor(proveedor01);
	 dirC.setProveedor(proveedor02);
	 
	 Set<Direccion> direcciones1 = new HashSet<>();
	 Set<Direccion> direcciones2 = new HashSet<>();
	 
	 direcciones1.add(dirA);
	 direcciones1.add(dirB);
	 direcciones2.add(dirC);
	 
	 proveedor01.setDirecciones(direcciones1);
	 proveedor02.setDirecciones(direcciones2);
	 
	 proveedorRepository.save(proveedor01);
	 proveedorRepository.save(proveedor02);

*/
	}
}
