package tech.cognity.ventas.converter;

import org.springframework.stereotype.Component;

import tech.cognity.ventas.DTO.CarritoComprasDTO;
import tech.cognity.ventas.entity.carritoCompra;

@Component
public class CarritoCompraConverter extends AbstractConverter<carritoCompra, CarritoComprasDTO>{

	@Override
	public CarritoComprasDTO fromEntity(carritoCompra entity) {
		if(entity==null) return null;
		return CarritoComprasDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.cantidad(entity.getCantidad())
				.totalCompra(entity.getTotalCompra())
				.activo(entity.getActivo())
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
				.activo(dto.getActivo())
				.build();
	}

}
