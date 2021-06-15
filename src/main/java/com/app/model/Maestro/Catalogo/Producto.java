package com.app.model.Maestro.Catalogo;

import javax.persistence.*;
import javax.validation.constraints.Min;

import com.app.enums.Color;
import com.app.enums.Medida;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Producto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String codigo;
	
	//N Productos -> 1 Subfamilia
	@ManyToOne(optional=false)
	@JsonIgnoreProperties("productos")
	private Subfamilia subfamilia;
	
	private String marca;
	private String descripcion;
	
	@Enumerated(EnumType.STRING)
    private Medida medida;
	@Enumerated(EnumType.STRING)
    private Color color;
	
	@Min(0)
	private int precio;
	private boolean estado;
	
	//GET AND SET
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
	public Subfamilia getSubfamilia() {
		return subfamilia;
	}
	public void setSubfamilia(Subfamilia subfamilia) {
		this.subfamilia = subfamilia;
	}
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	
	public Medida getMedida() {
		return medida;
	}
	public void setMedida(Medida medida) {
		this.medida = medida;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	

}
