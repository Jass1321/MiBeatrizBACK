package com.app.model.Maestro.Catalogo;

import java.util.Set;
import java.util.HashSet;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Familia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(unique = true)
	private String nombre;
	
	//Una familia tiene muchas sub-familias
	@OneToMany(mappedBy = "familia", fetch = FetchType.EAGER)
	private Set<Subfamilia> subfamilias = new HashSet<>();


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

	public Set<Subfamilia> getSubfamilias() {
		return subfamilias;
	}

	public void setSubfamilias(Set<Subfamilia> subfamilias) {
		this.subfamilias = subfamilias;
	}
}
