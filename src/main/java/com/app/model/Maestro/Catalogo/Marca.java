package com.app.model.Maestro.Catalogo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.app.model.Inventario.Producto;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "marcas")
public class Marca implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(unique = true)
	private String nombre;


	//1 Sub-Familia -> N Productos*/
	@OneToMany(mappedBy = "marca" )
	@JsonIgnore
	private Set<Producto> productos = new HashSet<>();
	
	
	/* CONSTRUCTOR*/
	public Marca() {
		
	}

	

	public Marca( @NotNull String nombre) {
		super();
		this.nombre = nombre;
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
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Set<Producto> getProductos() {
		return productos;
	}


	public void setProductos(Set<Producto> productos) {
		this.productos = productos;
	}
	
	
}
