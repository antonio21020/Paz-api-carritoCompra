package tech.cognity.ventas.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import tech.cognity.ventas.entity.carritoCompra;

public interface CarritoCompraService {
	public List<carritoCompra> findAll(Pageable page);
	public List<carritoCompra> findByNombre(String nombre ,Pageable page);
	public carritoCompra findById(int id);
	public carritoCompra save(carritoCompra carritocompra);
	public carritoCompra update(carritoCompra carritocompra);
	public void delete(int id);
	List<carritoCompra> findById(Integer Id, Pageable page);
}
