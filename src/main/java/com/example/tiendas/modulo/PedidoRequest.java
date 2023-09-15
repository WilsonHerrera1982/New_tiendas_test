package com.example.tiendas.modulo;

import java.util.List;

public class PedidoRequest {
	private Long clienteId;
    private List<DetallePedidoRequest> detalles;
    
	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	public List<DetallePedidoRequest> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<DetallePedidoRequest> detalles) {
		this.detalles = detalles;
	}
}
