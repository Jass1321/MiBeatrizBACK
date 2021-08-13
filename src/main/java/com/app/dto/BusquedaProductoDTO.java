package com.app.dto;

public class BusquedaProductoDTO {
	private String familia;
    private String subfamilia;
    
    private String codigo;
    private String marca;
    private String nombre;
    
    private String estado;
    
    private String color;
    private String medida;
    
    private Integer precioDesde;
    private Integer precioHasta;
    
    
	public String getFamilia() {
		return familia;
	}
	public void setFamilia(String familia) {
		this.familia = familia;
	}
	public String getSubfamilia() {
		return subfamilia;
	}
	public void setSubfamilia(String subfamilia) {
		this.subfamilia = subfamilia;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
	public String getMedida() {
		return medida;
	}
	public void setMedida(String medida) {
		this.medida = medida;
	}
	public Integer getPrecioDesde() {
		return precioDesde;
	}
	public void setPrecioDesde(Integer precioDesde) {
		this.precioDesde = precioDesde;
	}
	public Integer getPrecioHasta() {
		return precioHasta;
	}
	public void setPrecioHasta(Integer precioHasta) {
		this.precioHasta = precioHasta;
	}

    
}
