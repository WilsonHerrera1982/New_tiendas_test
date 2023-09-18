package com.example.tiendas.dto;


import java.util.Date;
import java.util.List;

import com.example.tiendas.modulo.Cliente;
import com.example.tiendas.modulo.Producto;

public class PedidoDto {

	private Long id;
    private Date fecha;
    private String estado;
    private Cliente cliente;
    private List<DetallePedidoDto> detalles;
    
    
    
    public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public String getEstado() {
		return estado;
	}



	public void setEstado(String estado) {
		this.estado = estado;
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



	public List<DetallePedidoDto> getDetalles() {
		return detalles;
	}



	public void setDetalles(List<DetallePedidoDto> detalles) {
		this.detalles = detalles;
	}



	
   
}
