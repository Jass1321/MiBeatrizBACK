package com.app.dto.Maestro.Tercero;

import java.util.List;

import com.app.model.Maestro.Almacen;
import com.app.model.Maestro.Inventario;
import com.app.model.Maestro.Tercero.DireccionTercero;
import com.app.model.Maestro.Tercero.Proveedor;

public class AlmacenDTO {

	Almacen almacen;
	List<Inventario> inventario;
	public Almacen getAlmacen() {
		return almacen;
	}
	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}
	public List<Inventario> getInventario() {
		return inventario;
	}
	public void setInventario(List<Inventario> inventario) {
		this.inventario = inventario;
	}
	
	Proveedor proveedor;
	List<DireccionTercero> direccion;
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
	
	
	
}
