package com.app.model.Inventario;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.app.enums.Color;
import com.app.enums.Medida;
import com.app.model.Maestro.Catalogo.Marca;
import com.app.model.Maestro.Catalogo.Subfamilia;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "productos")
public class Producto implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String codigo;
	
	@NotNull
	@Column(unique = true)
	private String nombre;
	
	//N Productos -> 1 Subfamilia
	@ManyToOne
	//@JsonIgnoreProperties("productos")
	@JoinColumn(name = "subfamilia_id")
	private Subfamilia subfamilia;
	
    //N Productos -> 1 Subfamilia
	@ManyToOne
	@JoinColumn(name = "marca_id")
	//@JsonIgnoreProperties("productos")
	private Marca marca;
	
	@Enumerated(EnumType.STRING)
    private Medida medida;
	@Enumerated(EnumType.STRING)
    private Color color;
	
	@Min(0)
	private int precio;
	private boolean estado;
	
	public Producto() {
		
	}
	

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
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public Subfamilia getSubfamilia() {
		return subfamilia;
	}
	public void setSubfamilia(Subfamilia subfamilia) {
		this.subfamilia = subfamilia;
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
