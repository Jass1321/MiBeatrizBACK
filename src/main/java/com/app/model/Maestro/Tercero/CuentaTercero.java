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
public class CuentaTercero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String cci;
	private String num;
	private String entidad;
	private String moneda;
	private String tipo_cuenta;
	/*
	//N Cuenta -> 1 Tercero
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "proveedor_id")
	@JsonIgnore
	private Proveedor proveedor;

	public Cuenta() {
		
	}
	
	public Cuenta(String cci, String num, String entidad, String moneda, String tipo_cuenta, Proveedor proveedor) {
		this.cci = cci;
		this.num = num;
		this.entidad = entidad;
		this.moneda = moneda;
		this.tipo_cuenta = tipo_cuenta;
		this.proveedor = proveedor;
	}
	
		public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
*/
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCci() {
		return cci;
	}

	public void setCci(String cci) {
		this.cci = cci;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getEntidad() {
		return entidad;
	}

	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getTipo_cuenta() {
		return tipo_cuenta;
	}

	public void setTipo_cuenta(String tipo_cuenta) {
		this.tipo_cuenta = tipo_cuenta;
	}


}
