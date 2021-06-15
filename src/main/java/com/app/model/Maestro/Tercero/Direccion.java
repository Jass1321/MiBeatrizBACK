package com.app.model.Maestro.Tercero;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Direccion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String domicilio;
	private String pais;
	private String departamento;
	private String provincia;
	private String distrito;
	private String ubigeo;
	
	//N Direccion -> 1 Tercero
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proveedor_id")
	@JsonIgnore
	private Proveedor proveedor;

	/*
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "cliente_id")
	@JsonIgnore
	private Cliente cliente;
*/
	public Direccion() {
	}

	public Direccion(String domicilio, String pais, String departamento, String provincia, String distrito,
			String ubigeo, Proveedor proveedor) {
		this.domicilio = domicilio;
		this.pais = pais;
		this.departamento = departamento;
		this.provincia = provincia;
		this.distrito = distrito;
		this.ubigeo = ubigeo;
		this.proveedor = proveedor;
	}

	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getUbigeo() {
		return ubigeo;
	}

	public void setUbigeo(String ubigeo) {
		this.ubigeo = ubigeo;
	}


	
}
