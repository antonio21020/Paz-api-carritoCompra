package tech.cognity.ventas.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarritoComprasDTO {
	private int id;
	private String nombre;
	private int cantidad;
	private double totalCompra;
	private Boolean activo;
}
