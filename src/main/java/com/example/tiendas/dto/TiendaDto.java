package com.example.tiendas.dto;

import java.util.List;

import lombok.Data;

public class TiendaDto {
	private Long id;
    private String nombre;
    private String direccion;
    private String telefono;
    private List<ProductoDto> productos;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public List<ProductoDto> getProductos() {
		return productos;
	}
	public void setProductos(List<ProductoDto> productos) {
		this.productos = productos;
	}
    
    
}
