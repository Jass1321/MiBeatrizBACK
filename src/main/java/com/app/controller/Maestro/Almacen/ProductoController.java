package com.app.controller.Maestro.Almacen;

import java.util.List;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dto.BusquedaProductoDTO;
import com.app.criteria.ProductoCriteria;
import com.app.enums.Color;
import com.app.enums.Medida;
import com.app.model.Inventario.Producto;
import com.app.model.Inventario.ProductoImage;
import com.app.repository.Almacen.ProductoImageRepository;
import com.app.repository.Almacen.ProductoRepository;
import com.app.service.Almacen.ProductoService;

import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.StringFilter;
import org.apache.commons.lang3.StringUtils;


@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {

	@Autowired
	ProductoService productoService;
	
	@Autowired
	ProductoRepository productoRepository;
	
	@Autowired
	ProductoImageRepository productoImgRepository;
	
	
	
	@PostMapping("/upload")
	public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		ProductoImage img = new ProductoImage(
				file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));
		productoImgRepository.save(img);
		return ResponseEntity.status(HttpStatus.OK);
	}
	
	@GetMapping(path = { "/get/{imageName}" })
	public ProductoImage getImage(@PathVariable("imageName") String imageName) throws IOException {
		final Optional<ProductoImage> retrievedImage = productoImgRepository.findByName(imageName);
		ProductoImage img = new ProductoImage(retrievedImage.get().getName(), retrievedImage.get().getType(),
				decompressBytes(retrievedImage.get().getPicByte()));
		return img;
	}
	
	// compress the image bytes before storing it in the database
		public static byte[] compressBytes(byte[] data) {
			Deflater deflater = new Deflater();
			deflater.setInput(data);
			deflater.finish();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			while (!deflater.finished()) {
				int count = deflater.deflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			try {
				outputStream.close();
			} catch (IOException e) {
			}
			System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
			return outputStream.toByteArray();
		}
		// uncompress the image bytes before returning it to the angular application
		public static byte[] decompressBytes(byte[] data) {
			Inflater inflater = new Inflater();
			inflater.setInput(data);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			try {
				while (!inflater.finished()) {
					int count = inflater.inflate(buffer);
					outputStream.write(buffer, 0, count);
				}
				outputStream.close();
			} catch (IOException ioe) {
			} catch (DataFormatException e) {
			}
			return outputStream.toByteArray();
		}
		
	
	
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
