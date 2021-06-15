package com.app.model.Maestro.Tercero;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String codigo;
	private int ruc;
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	private String nombre;
	private String rubro;
	private String comentario;
	/*
	//1 tercero -> N Direccion
	@OneToMany(mappedBy = "cliente",  cascade = CascadeType.ALL)
	private Set<Direccion> direcciones = new HashSet<>();
	//1 tercero -> N Contacto
	@OneToMany(mappedBy = "cliente",  cascade = CascadeType.ALL)
	private Set<Contacto> contactos = new HashSet<>();
	
	public Cliente() {
		
	}

	public Cliente(String codigo, int ruc, Date fechaInicio, String nombre, String rubro, String comentario,
			Set<Direccion> direcciones, Set<Contacto> contactos) {
		super();
		this.codigo = codigo;
		this.ruc = ruc;
		this.fechaInicio = fechaInicio;
		this.nombre = nombre;
		this.rubro = rubro;
		this.comentario = comentario;
		this.direcciones = direcciones;
		this.contactos = contactos;
	}
	
	public Set<Direccion> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(Set<Direccion> direcciones) {
		this.direcciones = direcciones;
	}

	public Set<Contacto> getContactos() {
		return contactos;
	}

	public void setContactos(Set<Contacto> contactos) {
		this.contactos = contactos;
	}*/

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getRuc() {
		return ruc;
	}

	public void setRuc(int ruc) {
		this.ruc = ruc;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRubro() {
		return rubro;
	}

	public void setRubro(String rubro) {
		this.rubro = rubro;
	}

	
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	
}
