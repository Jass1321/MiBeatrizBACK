package com.app.controller.Maestro;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.NotFoundException;
import com.app.model.Maestro.Banco;
import com.app.model.Maestro.CuentaBancaria;
import com.app.repository.Maestro.BancoRepository;
import com.app.repository.Maestro.CuentaBancariaRepository;
import com.app.service.Maestro.BancoService;

@RestController
@RequestMapping("/bancos")
@CrossOrigin(origins = "http://localhost:4200")
public class BancoController {
	
	@Autowired
	private BancoService bancoService;
	
	@Autowired
	private BancoRepository bancoRepository;
	
	@Autowired
	private CuentaBancariaRepository cuentaRepository;
	
	/*----------READ----------*/
	//READ DEP SIMPLE
	@GetMapping("/listSelectBanco")
	private ResponseEntity<List<Banco>> getListBancos(){
		return ResponseEntity.ok(bancoService.listBanco());
	}

	//READ DEP WITH PAGE
	@GetMapping("/listCuentaBancaria")
	public ResponseEntity<Page<CuentaBancaria>> listAllCuentaBancaria(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10")int size,
			@RequestParam(defaultValue = "nombre") String order,
		    @RequestParam(defaultValue = "true") boolean asc
    ){
    	Page<CuentaBancaria> cuenta = bancoService.listCuentaWithPage(
    			PageRequest.of(page,size, Sort.by(order))
		);
		if(!asc)
            cuenta = bancoService.listCuentaWithPage(
                    PageRequest.of(page, size, Sort.by(order).descending()));
		return new ResponseEntity<Page<CuentaBancaria>>(cuenta, HttpStatus.OK);
	}
	
	@GetMapping("/listBanco")
	public ResponseEntity<Page<Banco>> listAllBanco(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10")int size,
			@RequestParam(defaultValue = "nombre") String order,
		    @RequestParam(defaultValue = "true") boolean asc
    ){
    	Page<Banco> banco = bancoService.listBancopWithPage(
    			PageRequest.of(page,size, Sort.by(order))
		);
		if(!asc)
            banco = bancoService.listBancopWithPage(
                    PageRequest.of(page, size, Sort.by(order).descending()));
		return new ResponseEntity<Page<Banco>>(banco, HttpStatus.OK);
	}
	
	//READ DETAIL BY ID
	@GetMapping("/listBanco/{bancoId}")
	public Banco getBancosById(@PathVariable("bancoId") Long bancoId){
		return bancoService.getBancoById(bancoId);
	}
	
	//READ AREAS BY DEP ID 
	@GetMapping("/listBanco/{bancoId}/cuentas")
	public List<CuentaBancaria> getCuentasByDepId(
			@PathVariable("bancoId") Long bancoId){
		return cuentaRepository.findByBancoId(bancoId);
		
	}
	
	@GetMapping("/listDepa/{bancoId}/cuentas/{cuentaId}")
	public CuentaBancaria getCuentasIdByBancoId(
			@PathVariable(value = "bancoId") Long bancoId,
			@PathVariable(value = "cuentaId")Long cuentaId){
		return bancoService.getCuentaIdByIdBanco(cuentaId);
		
	}
	
	/*----------CREATE----------*/
	@PostMapping("/create")
	public Banco createBanco(@Validated @RequestBody Banco banco) {
		return bancoService.createeBanco(banco);
	}
	@PostMapping("/create/{bancoId}/cuentas")
	public CuentaBancaria createCuenta(@PathVariable(value = "bancoId") Long bancoId, @Validated @RequestBody CuentaBancaria cuenta) {
		return bancoRepository.findById(bancoId).map(
				banco -> {
						cuenta.setBanco(banco);
						return cuentaRepository.save(cuenta);
				}).orElseThrow(() -> new NotFoundException("Banco ID " + bancoId + " not found"));
	}
	
	@PostMapping("/save/cuentas")
	private ResponseEntity<CuentaBancaria> saveCuenta(@RequestBody CuentaBancaria cuenta){
		try {
			CuentaBancaria cuentaGuardada = bancoService.saveCuenta(cuenta);		
		return ResponseEntity.created(new URI("/cuentas"+cuentaGuardada.getId())).body(cuentaGuardada);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}	
	}
	
	/*----------UPDATE----------*/
	@PutMapping("/update/{bancoId}")
	public Banco updateDep(@PathVariable Long bancoId, @Validated @RequestBody Banco bancoDTO) {
		return bancoService.updateBanco(bancoId, bancoDTO) ;
	}
	
	@PutMapping("/update/{bancoId}/cuentas/{cuentaId}")
	public CuentaBancaria updateArea(
			@PathVariable(value = "bancoId") Long bancoId,
			@PathVariable(value = "cuentaId")Long cuentaId, 
			@Validated @RequestBody CuentaBancaria cuentaDTO) {
		
		if (!bancoRepository.existsById(bancoId)) {
			throw new NotFoundException("Banco ID " + bancoId + " not found");
		}
		return cuentaRepository.findById(cuentaId).map(
				cuenta -> {
					    cuenta.setNum(cuentaDTO.getNum());
						cuenta.setCci(cuentaDTO.getCci());
						cuenta.setMoneda(cuentaDTO.getMoneda());
						return cuentaRepository.save(cuenta);
				}).orElseThrow(() -> new NotFoundException("Cuenta ID " + cuentaId + "not found"));
	}
	
	/*----------DELETE----------*/
	@DeleteMapping("/delete/{bancoId}")
	public ResponseEntity<?> deleteBanco(@PathVariable(value = "bancoId") Long bancoId){
		return bancoService.deleteBanco(bancoId);
	}
	
	@DeleteMapping("/delete/{bancoId}/cuentas/{cuentaId}")
	public ResponseEntity<?> deleteCuenta(
			@PathVariable(value = "bancoId") Long bancoId,
			@PathVariable(value = "cuentaId") Long cuentaId) {
		return cuentaRepository.findByIdAndBancoId(cuentaId, bancoId).map(
				cuenta -> {
						cuentaRepository.delete(cuenta);
						return ResponseEntity.ok().build();
					}).orElseThrow(() -> new NotFoundException(
							"Cuenta Bancaria not found with ID " + cuentaId + " and Banco ID  " + bancoId));
				}
	 
	@DeleteMapping("/delete/cuentas/{id}")
	private ResponseEntity<Boolean> deleteCuentaBancaria (@PathVariable("id")Long id){
		bancoService.deleteByCuentaId(id);
		return ResponseEntity.ok(!(bancoService.findById(id)!=null));
		
	}
}
