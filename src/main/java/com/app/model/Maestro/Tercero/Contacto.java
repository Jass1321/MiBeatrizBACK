package com.app.model.Maestro.Tercero;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Contacto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nombre;
	private String cargo;
	private String correo;
	private String telefono;
	/*
	//N Contacto -> 1 Tercero
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "proveedor_id")
	@JsonIgnore
	private Proveedor proveedor;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "cliente_id")
	@JsonIgnore
	private Cliente cliente;

	public Contacto() {
		
	}
	
	public Contacto(String nombre, String cargo, String correo, String telefono, Proveedor proveedor, Cliente cliente) {
		this.nombre = nombre;
		this.cargo = cargo;
		this.correo = correo;
		this.telefono = telefono;
		this.proveedor = proveedor;
		this.cliente = cliente;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	*/

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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	
	
	
}
