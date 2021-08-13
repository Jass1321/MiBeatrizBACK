package com.app.controller.Maestro.Almacen;

import java.net.URI;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.StringFilter;

import com.app.dto.BusquedaProductoDTO;
import com.app.enums.Color;
import com.app.enums.Medida;
import com.app.criteria.ProductoCriteria;
import com.app.model.Inventario.Producto;
import com.app.repository.Almacen.ProductoImageRepository;
import com.app.repository.Almacen.ProductoRepository;
import com.app.service.Almacen.ProductoService;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {

	@Autowired
	private ProductoService productoService;
	
	@Autowired
	ProductoRepository productoRepository;
	
	@Autowired
	ProductoImageRepository productoImgRepository;
	
	/*----------READ----------*/
	@GetMapping("/listProducto")
	public ResponseEntity<List<Producto>> listProducto(){
		return ResponseEntity.ok(productoService.list());
	}

	/*----------READ WITH PAGE----------*/
	@GetMapping("/listProductoWithPage")
	public ResponseEntity<Page<Producto>> listProductoWithPage(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "12")int size,
			@RequestParam(defaultValue = "nombre") String order,
		    @RequestParam(defaultValue = "true") boolean asc
    ){
    	Page<Producto> p = productoService.listProductoWithPage(
    			PageRequest.of(page,size, Sort.by(order))
		);
		if(!asc)
            p = productoService.listProductoWithPage(
                    PageRequest.of(page, size, Sort.by(order).descending()));
		return new ResponseEntity<Page<Producto>>(p, HttpStatus.OK);
	}
	
	/*----------DETAIL----------*/
	@GetMapping("/detail/{id}")
	public Producto getProductosById(@PathVariable("id")Long id){
		return productoService.getProductoById(id);
	}
	
	/*----------CREATE----------*/
	@PostMapping("/create")
	public Producto createProducto(@RequestBody Producto p) {
		return productoService.save(p);
	}
	
	/*----------DELETE----------*/
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteProducto(@PathVariable(value = "id") Long id){
		return productoService.delete(id);
	}
	
	/*----------FILTERS----------*/
	@PostMapping("/filter")
	public ResponseEntity<List<Producto>> filterProducto(@RequestBody BusquedaProductoDTO busquedaDTO){
		ProductoCriteria productoCriteria = createCriteria(busquedaDTO);
		List<Producto> list = productoService.findByCriteria(productoCriteria);
		return new ResponseEntity<List<Producto>>(list, HttpStatus.OK);
	}
	
	private ProductoCriteria createCriteria(BusquedaProductoDTO dto) {
		ProductoCriteria productoCriteria = new ProductoCriteria();
		if(dto!=null) {
			//Tablas Relacionadas
			if(!StringUtils.isBlank(dto.getFamilia())){
                StringFilter filter = new StringFilter();
                filter.setEquals(dto.getFamilia());
                productoCriteria.setFamilia(filter);
            }
			if(!StringUtils.isBlank(dto.getSubfamilia())){
                StringFilter filter = new StringFilter();
                filter.setEquals(dto.getSubfamilia());
                productoCriteria.setSubfamilia(filter);
            }
			//String
			if(!StringUtils.isBlank(dto.getCodigo())){
                StringFilter filter = new StringFilter();
                filter.setContains(dto.getCodigo());
                productoCriteria.setCodigo(filter);
            }
			if(!StringUtils.isBlank(dto.getMarca())){
                StringFilter filter = new StringFilter();
                filter.setContains(dto.getMarca());
                productoCriteria.setMarca(filter);
            }
			if(!StringUtils.isBlank(dto.getNombre())){
                StringFilter filter = new StringFilter();
                filter.setContains(dto.getNombre());
                productoCriteria.setDescripcion(filter);
            }
			//Boolean
			if(!StringUtils.isBlank(dto.getEstado())){
                BooleanFilter filter = new BooleanFilter();
                switch (dto.getEstado()) {
	                case "true":
	                	filter.setEquals(true);
	                	break;
	                case "false":
	                	filter.setEquals(false);
	                	break;
	                default:
	                	filter.setEquals(false);
	                	break;
	            }
               productoCriteria.setEstado(filter);
            }
			//Enums
			if(!StringUtils.isBlank(dto.getColor())){
                ProductoCriteria.ColorFilter filter = new ProductoCriteria.ColorFilter();
                String color = dto.getColor().toUpperCase();
                filter.setEquals(Color.valueOf(color));
                productoCriteria.setColor(filter);
            }
			if(!StringUtils.isBlank(dto.getMedida())){
                ProductoCriteria.MedidaFilter filter = new ProductoCriteria.MedidaFilter();
                String medida = dto.getMedida().toUpperCase(); //Convertir en mayuscula
                filter.setEquals(Medida.valueOf(medida));
                productoCriteria.setMedida(filter);
            }
			//DESDE-HASTA Integer
			if(dto.getPrecioDesde()!=null || dto.getPrecioHasta()!=null){
                IntegerFilter filter = new IntegerFilter();
                if(dto.getPrecioDesde()!=null){
                    filter.setGreaterThanOrEqual(dto.getPrecioDesde());
                    productoCriteria.setPrecio(filter);
                }
                if(dto.getPrecioHasta()!=null){
                    filter.setLessThanOrEqual(dto.getPrecioHasta());
                    productoCriteria.setPrecio(filter);
                }
            }
			
		}
		return productoCriteria;
	}	
}
