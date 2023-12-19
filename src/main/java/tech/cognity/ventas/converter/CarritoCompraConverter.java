package tech.cognity.ventas.converter;

import tech.cognity.ventas.DTO.CarritoComprasDTO;
import tech.cognity.ventas.entity.carritoCompra;

public class CarritoCompraConverter extends AbstractConverter<carritoCompra, CarritoComprasDTO>{

	@Override
	public CarritoComprasDTO fromEntity(carritoCompra entity) {
		if(entity==null) return null;
		return CarritoComprasDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.cantidad(entity.getCantidad())
				.totalCompra(entity.getTotalCompra())
				.build();
	}

	@Override
	public carritoCompra fromDTO(CarritoComprasDTO dto) {
		if(dto==null) return null;
		return carritoCompra.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.cantidad(dto.getCantidad())
				.totalCompra(dto.getTotalCompra())
				.build();
	}

}
