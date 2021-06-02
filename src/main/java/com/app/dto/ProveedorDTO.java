package com.app.dto;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ProveedorDTO {

	@NotBlank
	private String codigo;
	@NotBlank
	private int ruc;
	private String razonSocial;
	private Date fechaInicio;
	private String rubroActividad;
	private String direccion;
	private int telefono;
	private String correo;
	
	public ProveedorDTO() {
	}

	
	
	public ProveedorDTO(@NotBlank String codigo, @NotBlank int ruc, String razonSocial, Date fechaInicio,
			String rubroActividad, String direccion, int telefono, String correo) {
		super();
		this.codigo = codigo;
		this.ruc = ruc;
		this.razonSocial = razonSocial;
		this.fechaInicio = fechaInicio;
		this.rubroActividad = rubroActividad;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
	}



	public String getRubroActividad() {
		return rubroActividad;
	}



	public void setRubroActividad(String rubroActividad) {
		this.rubroActividad = rubroActividad;
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
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	
	
	
}
