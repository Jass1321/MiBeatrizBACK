package com.app.controller.Maestro.Catalogo;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.app.dto.BusquedaProductoDTO;
import com.app.criteria.ProductoCriteria;
import com.app.enums.Color;
import com.app.enums.Medida;
import com.app.model.Maestro.Catalogo.Producto;
import com.app.service.Maestro.Catalogo.ProductoService;


import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.StringFilter;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {

	@Autowired
	ProductoService productoService;
	
	@PostMapping("/list")
	public ResponseEntity<List<Producto>> list(@RequestBody BusquedaProductoDTO busquedaDTO){
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
			if(!StringUtils.isBlank(dto.getDescripcion())){
                StringFilter filter = new StringFilter();
                filter.setContains(dto.getDescripcion());
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
