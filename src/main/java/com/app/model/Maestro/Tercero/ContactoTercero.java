package com.app.model.Maestro.Tercero;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "contacto_terceros")
public class ContactoTercero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	private String cargo;
	private String correo;
	private String telefono;
	
	//N Direccion -> 1 Tercero
	@ManyToOne
	@JoinColumn(name = "proveedor_id")
	private Proveedor proveedorId;

	//N Direccion -> 1 Tercero
	@ManyToOne()
	@JoinColumn(name = "cliente_id")
	private Cliente clienteId;
	 

	/* GET & SET*/
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Proveedor getProveedorId() {
		return proveedorId;
	}

	public void setProveedorId(Proveedor proveedorId) {
		this.proveedorId = proveedorId;
	}

	public Cliente getClienteId() {
		return clienteId;
	}

	public void setClienteId(Cliente clienteId) {
		this.clienteId = clienteId;
	}

	

}
