package com.example.tiendas.dto;

import com.example.tiendas.modulo.Producto;

public class DetallePedidoDto {
    private Long id;
    private ProductoDto producto;
    private int cantidad;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ProductoDto getProducto() {
		return producto;
	}
	public void setProducto(ProductoDto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

    // Constructor, getters y setters
}