package com.example.tiendas.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tiendas.modulo.Producto;
import com.example.tiendas.servicio.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	 @Autowired
	    private ProductoService productoService;

	    @GetMapping
	    public List<Producto> obtenerTodosLosProductos() {
	        return productoService.obtenerTodosLosProductos();
	    }

	    @GetMapping("/{id}")
	    public Producto obtenerProductoPorId(@PathVariable Long id) {
	        return productoService.obtenerProductoPorId(id);
	    }

	    @PostMapping
	    public Producto crearProducto(@RequestBody Producto producto) {
	        return productoService.crearProducto(producto);
	    }

	    @PutMapping("/{id}")
	    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
	        return productoService.actualizarProducto(id, producto);
	    }

	    @DeleteMapping("/{id}")
	    public void eliminarProducto(@PathVariable Long id) {
	        productoService.eliminarProducto(id);
	    }
}
