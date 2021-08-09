package com.app.dto.Maestro.Tercero;

import java.util.List;

import com.app.model.Maestro.Tercero.ContactoTercero;
import com.app.model.Maestro.Tercero.CuentaTercero;
import com.app.model.Maestro.Tercero.DireccionTercero;
import com.app.model.Maestro.Tercero.Proveedor;

public class ProveedorDTO {

	Proveedor proveedor;
	
	//1 tercero -> N Direccion
	//@OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
	List<DireccionTercero> direccion;
	
	//1 tercero -> N Contacto
	//@OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
	List<ContactoTercero> contacto;
	
	//1 tercero -> N Cuenta
	//@OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
	List<CuentaTercero> cuenta;
	
	/* GET & SET*/
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public List<DireccionTercero> getDireccion() {
		return direccion;
	}
	public void setDireccion(List<DireccionTercero> direccion) {
		this.direccion = direccion;
	}
	public List<ContactoTercero> getContacto() {
		return contacto;
	}
	public void setContacto(List<ContactoTercero> contacto) {
		this.contacto = contacto;
	}
	public List<CuentaTercero> getCuenta() {
		return cuenta;
	}
	public void setCuenta(List<CuentaTercero> cuenta) {
		this.cuenta = cuenta;
	}

}
