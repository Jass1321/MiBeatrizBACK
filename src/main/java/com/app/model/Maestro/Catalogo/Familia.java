package com.app.model.Maestro.Catalogo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "familias")
public class Familia implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(unique = true)
	private String nombre;
	
	//1 Familia -> N Sub-familias
	@OneToMany(mappedBy = "familia", fetch = FetchType.EAGER)
	private Set<Subfamilia> subfamilias = new HashSet<>();

	/* CONSTRUCTOR*/
	public Familia() {
		
	}
	
	public Familia(@NotNull String nombre) {
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

	public Set<Subfamilia> getSubfamilias() {
		return subfamilias;
	}

	public void setSubfamilias(Set<Subfamilia> subfamilias) {
		this.subfamilias = subfamilias;
	}
}
