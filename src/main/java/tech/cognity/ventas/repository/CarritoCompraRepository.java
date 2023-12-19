package tech.cognity.ventas.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.cognity.ventas.entity.carritoCompra;

@Repository
public interface CarritoCompraRepository extends JpaRepository<carritoCompra, Integer>{
	List<carritoCompra> findByIdContaining(Integer id,Pageable page);
}
