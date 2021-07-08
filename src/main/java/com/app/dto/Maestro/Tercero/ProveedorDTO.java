package com.app.dto.Maestro.Tercero;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import com.app.model.Maestro.Tercero.ContactoTercero;
import com.app.model.Maestro.Tercero.CuentaTercero;
import com.app.model.Maestro.Tercero.DireccionTercero;
import com.app.model.Maestro.Tercero.Proveedor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class ProveedorDTO {

	Proveedor proveedor;
	
	//1 tercero -> N Direccion
	//@OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
	List<DireccionTercero> direcciones;
	
	//1 tercero -> N Contacto
	//@OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
	List<ContactoTercero> contactos;
	
	//1 tercero -> N Cuenta
	//@OneToMany(mappedBy = "proveedor", cascade = CascadeType.ALL)
	List<CuentaTercero> cuentas;

	
	
	/* GET & SET*/
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}



	public List<DireccionTercero> getDirecciones() {
		return direcciones;
	}

	public void setDirecciones(List<DireccionTercero> direcciones) {
		this.direcciones = direcciones;
	}

	public List<ContactoTercero> getContactos() {
		return contactos;
	}

	public void setContactos(List<ContactoTercero> contactos) {
		this.contactos = contactos;
	}

	public List<CuentaTercero> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<CuentaTercero> cuentas) {
		this.cuentas = cuentas;
	}
}
