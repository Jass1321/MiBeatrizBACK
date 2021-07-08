package com.app.service.Maestro.Tercero;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.Maestro.Almacen;
import com.app.model.Maestro.Inventario;
import com.app.repository.Maestro.InventarioRepository;
import com.app.service.impl.Maestro.InventarioServiceImpl;


@Service
@Transactional
public class InventarioService  implements InventarioRepository {

	@Autowired
	private InventarioServiceImpl inventarioRepository;
	
	@Override
	
	public List<Inventario> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return (List<Inventario>) inventarioRepository.findAll();
	}

	@Override
	public Inventario findById(Long id) {
		// TODO Auto-generated method stub
		return inventarioRepository.findById(id).orElse(null);
	}

	@Override
	public Inventario save(Inventario inventario) {
		// TODO Auto-generated method stub
		return inventarioRepository.save(inventario);
	}

	@Override
	public List<Inventario> saveInventario(List<Inventario> inventarios) {
		// TODO Auto-generated method stub
		return (List<Inventario>) inventarioRepository.saveAll(inventarios);
	}

	@Override
	public List<Inventario> findByInventarioId(Almacen almacenId) {
		// TODO Auto-generated method stub
		return inventarioRepository.findByAlmacenId(almacenId);
	}

	@Override
	public boolean existDireccion(List<Inventario> inventarios, Long id) {
        boolean existe = false;
		
		for(int i=0; i<inventarios.size(); i++) {
			Inventario d = inventarios.get(i);
			if(d.getId() != null) {				
				if(d.getId().equals(id)) {
					existe = true;
					break;
				}
			}
		}
		
		return existe;
	}
	

}
