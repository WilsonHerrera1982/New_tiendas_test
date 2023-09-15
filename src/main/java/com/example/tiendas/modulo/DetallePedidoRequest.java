package com.example.tiendas.modulo;

public class DetallePedidoRequest {

	private Long productoId; // El ID del producto asociado al detalle del pedido.
    private int cantidad;    // La cantidad de productos en el detalle del pedido.

    // Getters y setters

    public Long getProductoId() {
        return productoId;
    }

    public void setProductoId(Long productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
