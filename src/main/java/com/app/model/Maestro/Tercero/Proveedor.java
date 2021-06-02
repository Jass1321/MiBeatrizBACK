package com.app.model.Maestro.Tercero;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "proveedor")
public class Proveedor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String codigo;
	@Column
	private int ruc;
	@Column
	private String razonSocial;
	@Column
	@Temporal(TemporalType.DATE)
	
	private Date fechaInicio;
	@Column
	private String direccion;
	@Column
	private String rubroActividad;
	@Column
	private int telefono;
	@Column
	private String correo;
	
	public Proveedor() {
	}

	
	
	public Proveedor(String codigo, int ruc, String razonSocial, Date fechaInicio, String direccion,
			String rubroActividad, int telefono, String correo) {
		super();
		this.codigo = codigo;
		this.ruc = ruc;
		this.razonSocial = razonSocial;
		this.fechaInicio = fechaInicio;
		this.direccion = direccion;
		this.rubroActividad = rubroActividad;
		this.telefono = telefono;
		this.correo = correo;
	}



	public String getRubroActividad() {
		return rubroActividad;
	}

	public void setRubroActividad(String rubroActividad) {
		this.rubroActividad = rubroActividad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
