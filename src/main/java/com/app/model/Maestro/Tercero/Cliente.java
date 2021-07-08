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
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(unique = true)
	private String codigo;
	@Column(unique = true)
	private String ruc;
	
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	private String razonSocial;
	private String rubroActividad;
	private String comentario;
	
	/* CONSTRUCTOR*/
	public Cliente() {
		
	}

	public Cliente(String codigo, String ruc, Date fechaInicio, String razonSocial, String rubroActividad,
				   String comentario) {
		super();
		this.codigo = codigo;
		this.ruc = ruc;
		this.fechaInicio = fechaInicio;
		this.razonSocial = razonSocial;
		this.rubroActividad = rubroActividad;
		this.comentario = comentario;
	}

	/* GET & SET*/
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

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getRubroActividad() {
		return rubroActividad;
	}

	public void setRubroActividad(String rubroActividad) {
		this.rubroActividad = rubroActividad;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
