package tech.cognity.ventas.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import tech.cognity.ventas.entity.carritoCompra;
import tech.cognity.ventas.exceptions.GeneralServiceException;
import tech.cognity.ventas.exceptions.NoDataFoundException;
import tech.cognity.ventas.exceptions.ValidateServiceException;
import tech.cognity.ventas.repository.CarritoCompraRepository;
import tech.cognity.ventas.service.CarritoCompraService;
import tech.cognity.ventas.validator.CarritoCompraValidator;

@Slf4j
@Service
public class CarritoCompraServiceImpl implements CarritoCompraService{
	@Autowired
	private CarritoCompraRepository repository;
	
	@Override
	@Transactional(readOnly=true)
	public List<carritoCompra> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		}catch (NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}
	@Override
	@Transactional(readOnly=true)
	public carritoCompra findById(int id) {
		try {
			carritoCompra registro =  repository.findById(id).orElseThrow(()->new NoDataFoundException("No existe el registro con el ID"));
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	public carritoCompra save(carritoCompra carritocompra) {
		try {
			CarritoCompraValidator.save(carritocompra);
			if(repository.findByNombre(carritocompra.getNombre())!=null) {
				throw new ValidateServiceException("Ya existe un registro con este nombre: "+carritocompra.getNombre());
			
			}
			carritocompra.setActivo(true);
			carritoCompra registro = repository.save(carritocompra);
			return registro;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	public carritoCompra update(carritoCompra carritocompra) {
		try {
			CarritoCompraValidator.save(carritocompra);
			carritoCompra registroA = repository.findById(carritocompra.getId()).orElseThrow(()->new NoDataFoundException("No existe el registro con ese ID"));
			carritoCompra registroB = repository.findByNombre(carritocompra.getNombre());
			if(registroB !=null && registroB.getId() != registroA.getId()) {
				throw new ValidateServiceException("Ya existe un registro con este nombre: "+carritocompra.getNombre());
			}
			registroA.setNombre(carritocompra.getNombre());
			registroA.setCantidad(carritocompra.getCantidad());
			registroA.setTotalCompra(carritocompra.getTotalCompra());
			repository.save(carritocompra);
			
			return registroA;
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	public void delete(int id) {
		try {
			carritoCompra carritocompra = repository.findById(id).orElseThrow(()->new NoDataFoundException("No exist el registro con ese ID"));
			repository.delete(carritocompra);
					
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}

	@Override
	public List<carritoCompra> findByNombre(String nombre, Pageable page) {
		try {
			return repository.findByNombreContaining(nombre,page);
		}  catch (ValidateServiceException |NoDataFoundException e) {
			log.info(e.getMessage(),e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			throw new GeneralServiceException(e.getMessage(),e);
		}
	}
	

}
