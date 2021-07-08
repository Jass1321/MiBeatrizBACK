package com.app.model.Maestro.Tercero;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "cuenta_terceros")
public class CuentaTercero implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String num;
	private String cci;
	private String entidad;
	private String moneda;
	private String tipoCuenta;
	
	//N Direccion -> 1 Tercero
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "proveedor_id")
	private Proveedor proveedorId;

	/*N Direccion -> 1 Tercero
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "cliente_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Cliente clienteId;
*
	
	/* GET & SET*/
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getCci() {
		return cci;
	}

	public void setCci(String cci) {
		this.cci = cci;
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

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public Proveedor getProveedorId() {
		return proveedorId;
	}

	public void setProveedorId(Proveedor proveedorId) {
		this.proveedorId = proveedorId;
	}

	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
