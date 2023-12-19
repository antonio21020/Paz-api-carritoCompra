package tech.cognity.ventas.validator;

import tech.cognity.ventas.entity.carritoCompra;

public class CarritoCompraValidator {
	public static void save(carritoCompra carritocompra) {
		if(carritocompra.getNombre()==null || carritocompra.getNombre().isEmpty()) {
			throw new RuntimeException("el nombre es requerido");
		}
		if(carritocompra.getNombre().length()>100) {
			throw new RuntimeException("el nombre es muy largo");
		}
		if(carritocompra.getCantidad()==null) {
			throw new RuntimeException("la cantidad de requerida");
		}
		if(carritocompra.getCantidad()<0) {
			throw new RuntimeException("la cantidad es incorrecta");
		}
	}

}
