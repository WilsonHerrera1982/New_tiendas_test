package com.example.tiendas.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tiendas.Excepciones.ClienteNotFoundException;
import com.example.tiendas.Excepciones.TiendaNotFoundException;
import com.example.tiendas.dto.ProductoDto;
import com.example.tiendas.dto.TiendaDto;
import com.example.tiendas.modulo.Producto;
import com.example.tiendas.modulo.Tienda;
import com.example.tiendas.repositorio.ProductoRepository;
import com.example.tiendas.repositorio.TiendaRepository;

import jakarta.transaction.Transactional;

@Service
public class TiendaService {
	@Autowired
	private TiendaRepository tiendaRepository;
	@Autowired
	private ProductoRepository productoRepository;
	private final ModelMapper modelMapper = new ModelMapper();
	

	public List<TiendaDto> obtenerTodasLasTiendas() {
	    List<Tienda> tiendas = tiendaRepository.findAll();
	    List<TiendaDto> tiendasDto = new ArrayList<>();

	    for (Tienda tienda : tiendas) {
	    	TiendaDto tiendaDto = new TiendaDto();
	        tiendaDto.setId(tienda.getId());
	        tiendaDto.setNombre(tienda.getNombre());
	        tiendaDto.setDireccion(tienda.getDireccion());
	        tiendaDto.setTelefono(tienda.getTelefono());

	        List<ProductoDto> productosDto = new ArrayList<>();

	        // Filtrar los productos de la tienda actual
	        List<Producto> productosDeTienda = tienda.getProductos();
	        for (Producto producto : productosDeTienda) {
	            ProductoDto productoDto = new ProductoDto();
	            productoDto.setId(producto.getId());
	            productoDto.setNombre(producto.getNombre());
	            productoDto.setPrecio(producto.getPrecio());
	            productoDto.setStock(producto.getStock());
	            productosDto.add(productoDto);
	        }

	        tiendaDto.setProductos(productosDto);
	        tiendasDto.add(tiendaDto);
	    }

	    return tiendasDto;
	}

	public Optional<TiendaDto> obtenerTiendaTestPorId(Long tiendaId) {
        Optional<Tienda> tiendaOptional = tiendaRepository.findById(tiendaId);
        return tiendaOptional.map(tienda -> modelMapper.map(tienda, TiendaDto.class));
    }
	public Tienda obtenerTiendaPorId(Long id) {
		Optional<Tienda> tienda = tiendaRepository.findById(id);
		if (tienda.isPresent()) {
			Tienda t=new Tienda();
			t= tienda.get();
			t.setProductos(new ArrayList<>());
			return t;
		} else {
			throw new TiendaNotFoundException("Tienda no encontrada");
		}
	}

	public Tienda crearTienda(Tienda tienda) {
		return tiendaRepository.save(tienda);
	}

	@Transactional
	public Tienda actualizarTienda(Long id, Tienda tiendaActualizada) {
		Tienda tiendaExistente = obtenerTiendaPorId(id);
		if (tiendaExistente.getId()== id) {
			tiendaExistente.setNombre(tiendaActualizada.getNombre());
			tiendaExistente.setDireccion(tiendaActualizada.getDireccion());
			tiendaExistente.setTelefono(tiendaActualizada.getTelefono());
			return tiendaRepository.save(tiendaExistente);
		} else {
			throw new ClienteNotFoundException("Cliente no encontrado");
		}
	}

	public void eliminarTienda(Long id) {
		Tienda tienda = obtenerTiendaPorId(id);
		tiendaRepository.delete(tienda);
	}

	public TiendaDto  obtenerTiendaConProductos(Long tiendaId) {
		// Obtén la tienda por su ID
	    Tienda tienda = tiendaRepository.findById(tiendaId)
	            .orElseThrow(() -> new TiendaNotFoundException("Tienda no encontrada"));

	    TiendaDto tiendaDto = new TiendaDto();
	    tiendaDto.setId(tienda.getId());
	    tiendaDto.setNombre(tienda.getNombre());
	    tiendaDto.setDireccion(tienda.getDireccion());
	    tiendaDto.setTelefono(tienda.getTelefono());

	    List<ProductoDto> productosDto = new ArrayList<>();
	    List<Producto> productos = obtenerProductos();
	    List<Producto> productosFiltrados = productos.stream()
	    	    .filter(producto -> producto.getTienda() != null && producto.getTienda().getId() == tiendaId)
	    	    .collect(Collectors.toList());
	    for (Producto producto : productosFiltrados) {
	        ProductoDto productoDto = new ProductoDto();
	        productoDto.setId(producto.getId());
	        productoDto.setNombre(producto.getNombre());
	        productoDto.setPrecio(producto.getPrecio());
	        productoDto.setStock(producto.getStock());
	        productosDto.add(productoDto);
	    }

	    tiendaDto.setProductos(productosDto);

	    return tiendaDto;
	}

	private List<Producto> obtenerProductos() {
		
		return productoRepository.findAll();
		
	}
}
