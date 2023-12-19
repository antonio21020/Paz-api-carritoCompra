package tech.cognity.ventas.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.cognity.ventas.entity.carritoCompra;
import tech.cognity.ventas.repository.CarritoCompraRepository;
import tech.cognity.ventas.service.CarritoCompraService;
import tech.cognity.ventas.validator.CarritoCompraValidator;

@Service
public class CarritoCompraServiceImpl implements CarritoCompraService{
	@Autowired
	private CarritoCompraRepository repository;
	
	@Override
	@Transactional(readOnly=true)
	public List<carritoCompra> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<carritoCompra> findById(Integer Id, Pageable page) {
		try {
			return repository.findByIdContaining(Id,page);
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly=true)
	public carritoCompra findById(int id) {
		try {
			carritoCompra registro= repository.findById(id).orElseThrow();
			return registro;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public carritoCompra save(carritoCompra carritocompra) {
		try {
			CarritoCompraValidator.save(carritocompra);
			carritocompra.setActivo(true);
			carritoCompra registro=repository.save(carritocompra);
			return registro;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public carritoCompra update(carritoCompra carritocompra) {
		try {
			CarritoCompraValidator.save(carritocompra);
			carritoCompra registro = repository.findById(carritocompra.getId()).orElseThrow();
			registro.setCantidad(carritocompra.getCantidad());
			registro.setTotalCompra(carritocompra.getTotalCompra());
			repository.save(registro);
			return registro;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public void delete(int id) {
		try {
			carritoCompra registro = repository.findById(id).orElseThrow();
			repository.delete(registro);
		}catch(Exception e) {
		}
		
	}

	@Override
	public List<carritoCompra> findByNombre(String nombre, Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
