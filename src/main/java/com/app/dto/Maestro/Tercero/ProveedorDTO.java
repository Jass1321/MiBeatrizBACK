package com.app.dto.Maestro.Tercero;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.app.model.Maestro.Tercero.Direccion;
import com.app.model.Maestro.Tercero.Proveedor;

public class ProveedorDTO {

	Proveedor proveedor;
	Set<Direccion> direccion;
	
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Set<Direccion> getDireccion() {
		return direccion;
	}

	public void setDireccion(Set<Direccion> direccion) {
		this.direccion = direccion;
	}
	
	/*---PROVEEDOR---*/
	@NotBlank
	private String codigo;
	@NotBlank
	private int ruc;
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	private String nombre;
	private String rubro;
	private String comentario;

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
	
	/*
	 * ---Direccion---
	
	private String domicilio;
	private String pais;
	private String departamento;
	private String provincia;
	private String distrito;
	private String ubigeo;
	
	*/
}
