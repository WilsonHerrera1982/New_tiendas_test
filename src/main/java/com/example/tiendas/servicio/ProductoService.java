package com.example.tiendas.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tiendas.Excepciones.ProductoNotFoundException;
import com.example.tiendas.modulo.Producto;
import com.example.tiendas.modulo.Tienda;
import com.example.tiendas.repositorio.ProductoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class ProductoService {

	@Autowired
	private ProductoRepository productoRepository;
	@PersistenceContext
	private EntityManager entityManager;

	public List<Producto> obtenerTodosLosProductos() {
		List<Producto> listaProductos = new ArrayList<>();
		listaProductos = productoRepository.findAll();
		listaProductos.forEach(producto -> producto.getTienda().setProductos(new ArrayList<Producto>()));
		return listaProductos;
	}

	public Producto obtenerProductoPorId(Long id) {
		Optional<Producto> producto = productoRepository.findById(id);
		if (producto.isPresent()) {
			Producto p = new Producto();
			p = producto.get();
			Tienda t = new Tienda();
			t.setDireccion(p.getTienda().getDireccion());
			t.setId(p.getTienda().getId());
			t.setNombre(p.getTienda().getNombre());
			t.setProductos(new ArrayList<>());
			t.setTelefono(p.getTienda().getTelefono());
			p.setTienda(t);
			return p;
		} else {
			throw new ProductoNotFoundException("Producto no encontrado");
		}
	}

	public Producto crearProducto(Producto producto) {
		return productoRepository.save(producto);
	}

	public Producto actualizarProducto(Long id, Producto productoActualizado) {
		try {
			Producto productoExistente = obtenerProductoPorId(id);

			if (productoExistente != null) {
				// Actualizar los atributos del producto existente con los valores del producto
				// actualizado
				productoExistente.setNombre(productoActualizado.getNombre());
				productoExistente.setPrecio(productoActualizado.getPrecio());
				productoExistente.setStock(productoActualizado.getStock());
				// Guardar el producto actualizado en la base de datos
				productoRepository.save(productoExistente);
				Tienda tienda = new Tienda();
				tienda.setId(productoExistente.getTienda().getId());
				tienda.setNombre(productoExistente.getTienda().getNombre());
				tienda.setDireccion(productoExistente.getTienda().getDireccion());
				tienda.setTelefono(productoExistente.getTienda().getTelefono());
				productoActualizado.setTienda(tienda);
				productoActualizado.setId(productoExistente.getId());
				return productoActualizado;
			} else {
				throw new ProductoNotFoundException("Producto no encontrado");
			}
		} catch (Exception e) {
			return productoActualizado;
		}
	}

	@Transactional
	public void eliminarProducto(Long id) {
		Optional<Producto> productoOptional = productoRepository.findById(id);

		if (productoOptional.isPresent()) {
			Producto producto = productoOptional.get();
			// Forzar la carga de la relación "tienda" si es EAGER
			entityManager.refresh(producto);
			// Establecer la relación "tienda" a null antes de eliminar
			producto.setTienda(null);

			productoRepository.delete(producto);
		} else {
			throw new ProductoNotFoundException("Producto no encontrado");
		}
	}

}
