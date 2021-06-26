package com.app.model.Maestro;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "areas")
public class Area implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(unique = true)
	private String nombre;
	
	/* N Area -> 1 Departamento */
	@ManyToOne
	@JoinColumn(name = "dep_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
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
