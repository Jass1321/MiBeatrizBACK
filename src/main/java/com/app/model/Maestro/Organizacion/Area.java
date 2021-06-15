package com.app.model.Maestro.Organizacion;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "areas")
public class Area implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String nombre;
	
	/* N Area -> 1 Departamento */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "dep_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Departamento dep;

	/* CONSTRUCTOR*/
	public Area() {
		
	}
	

	public Area(@NotNull String nombre, Departamento dep) {
		super();
		this.nombre = nombre;
		this.dep = dep;
	}


	/* GET & SET*/
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

	public Departamento getDep() {
		return dep;
	}


	public void setDep(Departamento dep) {
		this.dep = dep;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
