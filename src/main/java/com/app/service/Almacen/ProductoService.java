package com.app.service.Almacen;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.JoinType;
import io.github.jhipster.service.QueryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.criteria.ProductoCriteria;
import com.app.dto.NotFoundException;
import com.app.model.Inventario.Producto;
import com.app.model.Inventario.Producto_;
import com.app.model.Maestro.Catalogo.Familia_;
import com.app.model.Maestro.Catalogo.Marca_;
import com.app.model.Maestro.Catalogo.Subfamilia_;
import com.app.repository.Almacen.ProductoRepository;

@Service
@Transactional
public class ProductoService extends QueryService<Producto> {

	@Autowired
	ProductoRepository productoRepository;

    /*----------CONDITIONS----------*/
	public boolean existsById(Long id) {
		return productoRepository.existsById(id);
	}
	
	/*----------SEARCHS----------*/
	public Producto getProductoById(Long productoId) {
		Producto p = new Producto();
		p = productoRepository.findById(productoId)
				.orElseThrow(() -> new NotFoundException("Producto ID - " + productoId +"no existe"));
		return p;
	}
	
	public Optional<Producto> getById(Long id) {
		return productoRepository.findById(id);
	}
	
	/*----------READ----------*/
	public List<Producto> list() {
		return productoRepository.findAll();
	}
	
	/*----------READ WITH PAGE----------*/
	public Page<Producto> listProductoWithPage(Pageable pageable){
		return  productoRepository.findAll(pageable);
	}
	
	
	/*----------CREATE----------*/
	public Producto save(Producto p) {
		return productoRepository.save(p);
	}
	
	/*----------DELETE----------*/
	public ResponseEntity<?> delete(Long id) {
		Producto p = new Producto();
		p = productoRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Producto ID - " + id +"no existe"));
		productoRepository.delete(p);
		return ResponseEntity.ok().build();
	}
	
	/*----------FILTER ----------*/
	public List<Producto> findByCriteria(ProductoCriteria productoCriteria){
		final Specification<Producto> specification = createSpecification(productoCriteria);
		List<Producto> productos = productoRepository.findAll(specification);
		return productos;
	}

	/*----------CONDITIONS BY FILTER----------*/
	private Specification<Producto> createSpecification(ProductoCriteria criteria){
		Specification<Producto> specification = Specification.where(null);
		if(criteria != null) {
			
			//Para String
			if(criteria.getCodigo()!=null) {
				specification =
					specification.and(buildStringSpecification(criteria.getCodigo(), Producto_.codigo));	
			}
			if(criteria.getDescripcion()!=null) {
				specification =
					specification.and(buildStringSpecification(criteria.getDescripcion(), Producto_.nombre));	
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
			//Para Tabla llamar la data dentro de una tabla relacionada 
			if(criteria.getFamilia()!=null) {
				specification =
					specification.and(buildSpecification(criteria.getFamilia(),
							root->root.join(Producto_.subfamilia, JoinType.LEFT).join(Subfamilia_.familia, JoinType.LEFT).get(Familia_.nombre)));		
			}
			//1°FORMA- Tabla Relacionada BY identidad
			if(criteria.getSubfamilia()!=null) {
				specification =
					specification
					.and(buildReferringEntitySpecification(criteria.getSubfamilia(), Producto_.subfamilia, Subfamilia_.nombre));	
			}
			//2°FORMA- Tabla Relacionada by JOIN
			if(criteria.getMarca()!=null) {
				specification =
					specification.
					and(buildSpecification(criteria.getMarca(),
							root -> root.join(Producto_.marca, JoinType.LEFT).get(Marca_.nombre)));	
			}
			
			/*
			 if(criteria.getSubfamilia()!=null) {
				specification =
					specification
					.and(buildSpecification(criteria.getSubfamilia(),
							root -> root.join(Producto_.subfamilia, JoinType.LEFT).get(Subfamilia_.nombre)));	
			 }
			*/	
		}
		 return specification;
	}
}
