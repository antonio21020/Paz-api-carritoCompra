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

import tech.cognity.ventas.DTO.CarritoComprasDTO;
import tech.cognity.ventas.converter.CarritoCompraConverter;
import tech.cognity.ventas.entity.carritoCompra;
import tech.cognity.ventas.service.CarritoCompraService;
import tech.cognity.ventas.utils.WrapperResponse;

@RestController
@RequestMapping("/carritoCompra")
public class CarritoComprasController {
	@Autowired
	private CarritoCompraService service;
	
	@Autowired
	private CarritoCompraConverter carritoConverter;
	@GetMapping()
	public ResponseEntity<List<CarritoComprasDTO>> findAll(
			@RequestParam(value = "nombre", required = false,defaultValue = "")String nombre,
			@RequestParam(value = "offset", required = false,defaultValue = "0")int pageNumber,
			@RequestParam(value = "limit", required = false,defaultValue = "5")int pageSize
			
			){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<carritoCompra> carritocompra;
		if(nombre==null) {
			carritocompra=service.findAll(page);
		}else {
			carritocompra=service.findByNombre(nombre, page);
		}
		if(carritocompra.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		List<CarritoComprasDTO> carritosDTO = carritoConverter.fromEntity(carritocompra);
        return new WrapperResponse(true,"success", carritosDTO).createResponse(HttpStatus.OK);
	}
	@GetMapping(value="/{id}")
	public ResponseEntity<WrapperResponse<CarritoComprasDTO>> findById(@PathVariable("id") int id){
		carritoCompra carritos = service.findById(id);
		if (carritos ==null) {
			return ResponseEntity.notFound().build();
		}
		CarritoComprasDTO carritosDTO=carritoConverter.fromEntity(carritos);
		return new WrapperResponse<CarritoComprasDTO>(true,"success", carritosDTO).createResponse(HttpStatus.OK);
	}
    
    @PostMapping
	public ResponseEntity<CarritoComprasDTO> save(@RequestBody CarritoComprasDTO carritoDTO){
    	carritoCompra carritoss = service.save(carritoConverter.fromDTO(carritoDTO));
    	CarritoComprasDTO carritosDTO = carritoConverter.fromEntity(carritoss);
		return new WrapperResponse(true,"success", carritosDTO).createResponse(HttpStatus.CREATED);
	}
    
    @PutMapping(value = "/{id}")
	public ResponseEntity<CarritoComprasDTO> update(@PathVariable("id") int id, 
											@RequestBody CarritoComprasDTO carritosDTO) {

		carritoCompra registro = service.update(carritoConverter.fromDTO(carritosDTO));
		if (registro == null) {
			return ResponseEntity.notFound().build();
		}
		CarritoComprasDTO registroDTO = carritoConverter.fromEntity(registro);
		return new WrapperResponse(true,"Succes",registroDTO).createResponse(HttpStatus.OK);
	}
	@DeleteMapping(value="/{id}")
	public ResponseEntity<CarritoComprasDTO> delete(@PathVariable("id") int id){
		service.delete(id);
		return new WrapperResponse(true,"success", null).createResponse(HttpStatus.OK);
	}
	
	
	
}
