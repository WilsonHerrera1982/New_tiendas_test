package com.example.tiendas.servicio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tiendas.Excepciones.ClienteNotFoundException;
import com.example.tiendas.Excepciones.PedidoNotFoundException;
import com.example.tiendas.Excepciones.ProductoNotFoundException;
import com.example.tiendas.Excepciones.ProductoSinStockException;
import com.example.tiendas.modulo.Cliente;
import com.example.tiendas.modulo.DetallePedido;
import com.example.tiendas.modulo.Pedido;
import com.example.tiendas.modulo.PedidoRequest;
import com.example.tiendas.modulo.Producto;
import com.example.tiendas.repositorio.ClienteRepository;
import com.example.tiendas.repositorio.DetallePedidoRepository;
import com.example.tiendas.repositorio.PedidoRepository;
import com.example.tiendas.repositorio.ProductoRepository;
import com.example.tiendas.repositorio.TiendaRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private DetallePedidoRepository detallePedidoRepository;
	@Autowired
	private TiendaRepository tiendaRepository;
	@Autowired
	private ProductoRepository productoRepository;
	@Autowired
	private ClienteRepository clienteRepository;

	public List<Pedido> obtenerTodosLosPedidos() {
		return pedidoRepository.findAll();
	}

	public Pedido obtenerPedidoPorId(Long id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		if (pedido.isPresent()) {
			return pedido.get();
		} else {
			throw new PedidoNotFoundException("Pedido no encontrado");
		}
	}

	public Pedido crearPedido(Pedido pedido) {
			return pedidoRepository.save(pedido);
	}

	public Pedido actualizarPedido(Long id, Pedido pedidoActualizado) {
		Pedido pedidoExistente = obtenerPedidoPorId(id);
		// Aquí se puede realizar la lógica de actualización de los atributos del
		// pedidoExistente
		return pedidoRepository.save(pedidoExistente);
	}

	public void eliminarPedido(Long id) {
		Pedido pedido = obtenerPedidoPorId(id);
		pedidoRepository.delete(pedido);
	}

	public Pedido realizarPedido(PedidoRequest pedidoRequest) {
	    // Obtener el cliente y validar su existencia
	    Cliente cliente = obtenerClientePorId(pedidoRequest.getClienteId());

	    // Crear el pedido
	    Pedido pedido = new Pedido();
	    pedido.setCliente(cliente);
	    pedido.setFecha(new java.util.Date());
	    pedido.setEstado("Generado");
	    pedido = pedidoRepository.save(pedido);

	    // Crear una lista para mantener el pedido final
	    List<Pedido> pedidoFinal = new ArrayList<>();
	    pedidoFinal.add(pedido);

	    // Crear los detalles del pedido
	    List<DetallePedido> detalles = pedidoRequest.getDetalles().stream().map(detalleRequest -> {
	        Producto producto = obtenerProductoPorId(detalleRequest.getProductoId());
	        if(producto.getStock()>0 && detalleRequest.getCantidad()< producto.getStock()) {
	        producto.setStock(producto.getStock()-detalleRequest.getCantidad());
	        DetallePedido detalle = new DetallePedido();
	        detalle.setPedido(pedidoFinal.get(0)); // Usar el pedido final aquí
	        detalle.setProducto(producto);
	        detalle.setPrecioUnitario(producto.getPrecio());
	        detalle.setCantidad(detalleRequest.getCantidad());	
	        productoRepository.save(producto);
	        return detallePedidoRepository.save(detalle);
	        }else {
	        	throw new ProductoSinStockException("Producto sin stock disponible");
	        }
	    }).collect(Collectors.toList());

	    pedido.setDetalles(detalles);	    
	    return pedido;
	}

	public List<Pedido> obtenerPedidosPorClienteEnRangoDeFechas(Long clienteId, String fechaInicio, String fechaFin) {
		// Convertir las fechas de entrada a objetos Date si es necesario
		Date inicio = convertirAFecha(fechaInicio);
		Date fin = convertirAFecha(fechaFin);
	  // Obtener los pedidos del cliente en el rango de fechas
		return pedidoRepository.findByClienteIdAndFechaBetween(clienteId, inicio, fin);
	}

	private Date convertirAFecha(String fechaString) {
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            return dateFormat.parse(fechaString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
	}

	private Cliente obtenerClientePorId(Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		if (cliente.isPresent()) {
			return cliente.get();
		} else {
			throw new ClienteNotFoundException("Cliente no encontrado");
		}
	}

	private Producto obtenerProductoPorId(Long productoId) {
		Optional<Producto> producto = productoRepository.findById(productoId);
		if (producto.isPresent()) {
			return producto.get();
		} else {
			throw new ProductoNotFoundException("Producto no encontrado");
		}
	}
}
