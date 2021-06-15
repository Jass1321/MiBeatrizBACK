package com.app.model.Maestro.Catalogo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;

@Entity
public class Subfamilia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(unique = true)
	private String nombre;
	
	//N Sub-familias -> 1 Familia
	@ManyToOne(optional = false)
	@JsonIgnoreProperties("subfamilias")
	private Familia familia;
	
	//1 Sub-Familia -> N Productos
	@OneToMany(mappedBy = "subfamilia" )
	@JsonIgnore
	private Set<Producto> productos = new HashSet<>();
	
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

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	public Set<Producto> getProductos() {
		return productos;
	}

	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}
	
}
