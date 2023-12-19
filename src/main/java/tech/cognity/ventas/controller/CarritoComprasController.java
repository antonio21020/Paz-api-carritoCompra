package tech.cognity.ventas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tech.cognity.ventas.entity.carritoCompra;
import tech.cognity.ventas.service.CarritoCompraService;

@RestController
@RequestMapping("/carritoCompra")
public class CarritoComprasController {
	@Autowired
	private CarritoCompraService service;
	
	@GetMapping()
	public ResponseEntity<List<carritoCompra>> findAll(
			@RequestParam(value = "id", required = false,defaultValue = "")Integer id,
			@RequestParam(value = "offset", required = false,defaultValue = "0")int pageNumber,
			@RequestParam(value = "limit", required = false,defaultValue = "5")int pageSize
			
			){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<carritoCompra> carritocompra;
		if(id==null) {
			carritocompra=service.findAll(page);
		}else {
			carritocompra=service.findById(id, page);
		}
		if(carritocompra.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(carritocompra);
	}
	@GetMapping(value="/{id}")
	public ResponseEntity<carritoCompra> findById(@PathVariable("id")int id){
		carritoCompra carritocompra = service.findById(id);
		if(carritocompra==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(carritocompra);
	}
	@PostMapping()
	public ResponseEntity<carritoCompra> create(@RequestBody carritoCompra carritocompra){
		carritoCompra registro=service.save(carritocompra);
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<carritoCompra> update(@PathVariable("id")int id,@RequestBody carritoCompra carritocompra){
		carritoCompra registro=service.update(carritocompra);
		if(registro==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(registro);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<carritoCompra> delete(@PathVariable("id")int id){
		service.delete(id);
		return ResponseEntity.ok(null);
	}
	
	
	
}
