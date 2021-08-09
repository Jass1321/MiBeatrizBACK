package com.app.service.Almacen;

import com.app.criteria.ProductoCriteria;
import com.app.model.Inventario.Producto;
import com.app.model.Maestro.Catalogo.Familia_;

import com.app.model.Maestro.Catalogo.Producto_;
import com.app.model.Maestro.Catalogo.Subfamilia_;
import com.app.repository.Almacen.ProductoImageRepository;
import com.app.repository.Almacen.ProductoRepository;

import io.github.jhipster.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
//readOnly = true solo para lectura no CRUD
@Transactional(readOnly = true)
public class ProductoService extends QueryService<Producto>{

	@Autowired
	ProductoRepository productoRepository;

	@Autowired
	ProductoImageRepository productoImgRepository;
	
	public List<Producto> findByCriteria(ProductoCriteria productoCriteria){
		final Specification<Producto> specification = createSpecification(productoCriteria);
		List<Producto> productos = productoRepository.findAll(specification);
		return productos;
	}
	
	private Specification<Producto> createSpecification(ProductoCriteria criteria){
		Specification<Producto> specification = Specification.where(null);
		  if(criteria != null) {
		
			//Para String
			if(criteria.getCodigo()!=null) {
				specification =
					specification.and(buildStringSpecification(criteria.getCodigo(), Producto_.codigo));	
			}
			if(criteria.getMarca()!=null) {
				specification =
					specification.and(buildStringSpecification(criteria.getMarca(), Producto_.marca));	
			}
			if(criteria.getDescripcion()!=null) {
				specification =
					specification.and(buildStringSpecification(criteria.getDescripcion(), Producto_.descripcion));	
			}
			//Para enums
			if(criteria.getColor()!=null) {
				specification =
					specification.and(buildSpecification(criteria.getColor(), Producto_.color));	
			}
			
			if(criteria.getMedida()!=null) {
				specification =
					specification.and(buildSpecification(criteria.getMedida(), Producto_.medida));	
			}
			//Para Integer
			if(criteria.getPrecio()!=null) {
				specification =
					specification.and(buildRangeSpecification(criteria.getPrecio(), Producto_.precio));	
			}
			//Para Boolean
			if(criteria.getEstado()!=null) {
				specification =
					specification.and(buildSpecification(criteria.getEstado(), Producto_.estado));	
			}
			//Para Tablas Relacionadas
			if(criteria.getSubfamilia()!=null) {
				specification =
					specification
					.and(buildReferringEntitySpecification(criteria.getSubfamilia(), Producto_.subfamilia, Subfamilia_.nombre));	
			}
			/*
			 if(criteria.getSubfamilia()!=null) {
				specification =
					specification
					.and(buildSpecification(criteria.getSubfamilia(),
							root -> root.join(Producto_.subfamilia, JoinType.LEFT).get(Subfamilia_.nombre)));	
			 }
			*/
			if(criteria.getFamilia()!=null) {
				specification =
					specification.and(buildSpecification(criteria.getFamilia(),
							root->root.join(Producto_.subfamilia, JoinType.LEFT).join(Subfamilia_.familia, JoinType.LEFT).get(Familia_.nombre)));		
			}
			
		}
		return specification;
	}
	
	
	
}
