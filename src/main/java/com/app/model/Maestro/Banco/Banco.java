package com.app.model.Maestro.Banco;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Banco {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nombre;
	
	public Banco() {
		
	}
	
	public Banco(long id, String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}
