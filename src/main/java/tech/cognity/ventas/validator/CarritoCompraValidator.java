package tech.cognity.ventas.validator;

import tech.cognity.ventas.entity.carritoCompra;
import tech.cognity.ventas.exceptions.ValidateServiceException;

public class CarritoCompraValidator {
	public static void save(carritoCompra carritocompra) {
		if(carritocompra.getNombre()==null || carritocompra.getNombre().isEmpty()) {
			throw new ValidateServiceException("el nombre es requerido");
		}
		if(carritocompra.getNombre().length()>100) {
			throw new ValidateServiceException("el nombre es muy largo");
		}
		if(carritocompra.getCantidad()==null) {
			throw new ValidateServiceException("la cantidad de requerida");
		}
		if(carritocompra.getCantidad()<0) {
			throw new ValidateServiceException("la cantidad es incorrecta");
		}
	}

}
