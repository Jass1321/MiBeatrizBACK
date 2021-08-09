package com.app.dto.Maestro.Tercero;

import java.util.List;

import com.app.model.Maestro.Tercero.ContactoTercero;
import com.app.model.Maestro.Tercero.DireccionTercero;
import com.app.model.Maestro.Tercero.Cliente;

public class ClienteDTO {

	Cliente cliente;
	
	//1 cliente -> N Direccion
	List<DireccionTercero> direccion;
	
	//1 cliente -> N Contacto
	List<ContactoTercero> contacto;

	/* GET & SET*/
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
	
	
	
}
