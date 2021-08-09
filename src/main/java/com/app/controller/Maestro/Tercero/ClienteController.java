package com.app.controller.Maestro.Tercero;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.Maestro.Tercero.ClienteDTO;
import com.app.model.Maestro.Tercero.Cliente;
import com.app.model.Maestro.Tercero.ContactoTercero;
import com.app.model.Maestro.Tercero.DireccionTercero;
import com.app.model.Maestro.Tercero.Proveedor;
import com.app.repository.Maestro.Tercero.ContactoTerceroRepository;
import com.app.repository.Maestro.Tercero.CuentaTerceroRepository;
import com.app.repository.Maestro.Tercero.DireccionTerceroRepository;
import com.app.service.Maestro.Tercero.ClienteService;
import com.app.service.Maestro.Tercero.ContactoTerceroService;
import com.app.service.Maestro.Tercero.CuentaTerceroService;
import com.app.service.Maestro.Tercero.DireccionTerceroService;
@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "http://localhost:4200")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	@Autowired
	DireccionTerceroService direccionService;
	
	@Autowired
	CuentaTerceroService cuentaService;
	
	@Autowired
	ContactoTerceroService contactoService;
	
	/*----------REPO----------*/
	@Autowired
	DireccionTerceroRepository direccionRepository;
	
	@Autowired
	ContactoTerceroRepository contactoRepository;
	
	@Autowired
	CuentaTerceroRepository cuentaRepository;

	/*----------READ----------*/
	//READ BASCIC
	@GetMapping("/listCliente")
	public ResponseEntity<List<Cliente>> getlistProveedor(){
		return ResponseEntity.ok(clienteService.listCliente());
	}
	
	//READ WITH PAGE
	@GetMapping("/listClienteWithPage")
	public ResponseEntity<Page<Cliente>> listAllCliente(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10")int size,
			@RequestParam(defaultValue = "nombre") String order,
		    @RequestParam(defaultValue = "true") boolean asc
    ){
		Page<Cliente> cliente = clienteService.listClienteWithPage(
			PageRequest.of(page,size, Sort.by(order))
		);
		if(!asc)
            cliente = clienteService.listClienteWithPage(
                    PageRequest.of(page, size, Sort.by(order).descending()));
		return new ResponseEntity<Page<Cliente>>(cliente,  HttpStatus.OK);
	}
	
	//READ ALL CLIENTE 
	@GetMapping("/detail/{id}")
	public  ResponseEntity<?> showAll(@PathVariable Long id){
		Map<String, Object> response = new HashMap<>();

        Cliente cliente = clienteService.findById(id);
        List<DireccionTercero> direcciones = direccionService.findByCliente(cliente);
        List<ContactoTercero> contactos = contactoService.findByCliente(cliente);

        response.put("cliente", cliente);
		response.put("direccion", direcciones);
		response.put("contacto", contactos);  
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	//READ DETAIL BY ID
	@GetMapping("/detailById/{id}")
	public Cliente getClientesById(@PathVariable("id")Long id){
		return clienteService.getCliById(id);
	}
	
	//READ DETAIL BY RUC
	@GetMapping("/detailByRuc/{ruc}")
    public Cliente getClientesByRuc(@PathVariable("ruc") String ruc){
		return clienteService.getCliByRuc(ruc);
    }
	
	//GENERAR CODIGO
	@GetMapping("/generateID")
    public ResponseEntity<?> generateCodigo() {
		Map<String, Object> response = new HashMap<>();
		response.put("identificador", clienteService.obtenerCodigo());
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

	/*----------CREATE----------*/
	//CREATE PROVEEDOR WITH DIRECCION - CONTACTO - CUENTA
    @PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody ClienteDTO clienteDTO){
		
    	Map<String, Object> response = new HashMap<>();
	
        Cliente clienteNew = clienteService.createCliente(clienteDTO.getCliente());
        
        List<DireccionTercero> direcciones = clienteDTO.getDireccion();
        List<DireccionTercero> direccionNew = null;
		    if(direcciones != null) {
		 	   for(DireccionTercero direccion: direcciones) {
		 		  direccion.setClienteId(clienteNew);
		 	   }
		 	   direccionNew = direccionService.saveDireccion(direcciones);
		    }
	    
	    List<ContactoTercero> contactos = clienteDTO.getContacto();
        List<ContactoTercero> contactoNew = null;
	        if(contactos != null) {
	     	   for(ContactoTercero contacto: contactos) {
	     		  contacto.setClienteId(clienteNew);
	     	   }
	     	  contactoNew = contactoService.saveContacto(contactos);
	        }
	        
	  
        response.put("cliente", clienteNew);
        response.put("direccion", direccionNew);
        response.put("contacto", contactoNew);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
	
	/*----------UPDATE----------*/
	//UPDATE PROVEEDOR WITH DIRECCION - CONTACTO - CUENTA
	 @PutMapping("/update/{id}")
	 @ResponseStatus(HttpStatus.CREATED)
	 public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ClienteDTO clienteDTO){
		
		Map<String, Object> response = new HashMap<>();
					
		Cliente clienteUp = clienteDTO.getCliente();
		Cliente clienteNow = clienteService.findById(id);
		   
		clienteNow.setRuc(clienteUp.getRuc());
		clienteNow.setFechaInicio(clienteUp.getFechaInicio());
		clienteNow.setRazonSocial(clienteUp.getRazonSocial());
		clienteNow.setRubroActividad(clienteUp.getRubroActividad());
		clienteNow.setComentario(clienteUp.getComentario());
		
		List<DireccionTercero> direccionUp = clienteDTO.getDireccion();
		List<DireccionTercero> direccionNow = direccionRepository.findByClienteId(clienteNow);
        List<DireccionTercero> direccionEdit = direccionService.updateDireccion(direccionUp, direccionNow, clienteNow);
	        
        List<ContactoTercero>  contactoUp = clienteDTO.getContacto();
        List<ContactoTercero>  contactoNow = contactoRepository.findByClienteId(clienteNow);
        List<ContactoTercero>  contactoEdit = contactoService.updateContacto(contactoUp, contactoNow, clienteNow);
	        
       
        Cliente clienteEdit = clienteService.createCliente(clienteNow);
      
        response.put("clinete", clienteEdit);
        response.put("direccion", direccionEdit);
        response.put("contacto", contactoEdit);
        
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	 }
	 
	//DELETE PROVEEDOR 
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id){
		Cliente cliente = clienteService.findById(id);
		direccionService.deleteByClienteId(cliente);
		contactoService.deleteByClienteId(cliente);
		return ResponseEntity.ok(!(clienteService.deleteCliente(id)!=null));	
    }
	
}
