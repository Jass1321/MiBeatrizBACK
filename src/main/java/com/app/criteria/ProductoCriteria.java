package com.app.criteria;

import com.app.enums.Color;
import com.app.enums.Medida;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.StringFilter;

public class ProductoCriteria {

	public static class ColorFilter extends Filter<Color>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;}
    public static class MedidaFilter extends Filter<Medida>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;}
    private StringFilter familia;
    private StringFilter subfamilia;
    private StringFilter codigo;
    private StringFilter marca;
    private StringFilter descripcion;
    
	private ColorFilter color;
	private MedidaFilter medida;
	
	private IntegerFilter precio;
	private BooleanFilter estado;
	
	//getter and setter
	public StringFilter getFamilia() {
		return familia;
	}
	public void setFamilia(StringFilter familia) {
		this.familia = familia;
	}
	public StringFilter getSubfamilia() {
		return subfamilia;
	}
	public void setSubfamilia(StringFilter subfamilia) {
		this.subfamilia = subfamilia;
	}
	public StringFilter getCodigo() {
		return codigo;
	}
	public void setCodigo(StringFilter codigo) {
		this.codigo = codigo;
	}
	public StringFilter getMarca() {
		return marca;
	}
	public void setMarca(StringFilter marca) {
		this.marca = marca;
	}
	public StringFilter getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(StringFilter descripcion) {
		this.descripcion = descripcion;
	}
	public ColorFilter getColor() {
		return color;
	}
	public void setColor(ColorFilter color) {
		this.color = color;
	}
	
	
	public MedidaFilter getMedida() {
		return medida;
	}
	public void setMedida(MedidaFilter medida) {
		this.medida = medida;
	}
	public IntegerFilter getPrecio() {
		return precio;
	}
	public void setPrecio(IntegerFilter precio) {
		this.precio = precio;
	}
	public BooleanFilter getEstado() {
		return estado;
	}
	public void setEstado(BooleanFilter estado) {
		this.estado = estado;
	} 
	
	
	
}
