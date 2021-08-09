package com.app.model.Maestro.Catalogo;

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
@Table(name = "servicios")
public class Servicio implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(unique = true)
	private String nombre;
	
	/* N Area -> 1 Departamento */
	@ManyToOne
	@JoinColumn(name = "tipo_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private TipoServicio tipo;

	/* CONSTRUCTOR*/
	public Servicio() {
		
	}
	
	public Servicio(@NotNull String nombre, TipoServicio tipo) {
		super();
		this.nombre = nombre;
		this.tipo = tipo;
	}

	/* GET & SET*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoServicio getTipoServicio() {
		return tipo;
	}

	public void setTipoServicio(TipoServicio tipo) {
		this.tipo = tipo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
