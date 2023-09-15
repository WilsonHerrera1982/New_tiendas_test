package com.tiendas.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.tiendas.modulo.Pedido;
import com.example.tiendas.repositorio.ClienteRepository;
import com.example.tiendas.repositorio.DetallePedidoRepository;
import com.example.tiendas.repositorio.PedidoRepository;
import com.example.tiendas.repositorio.ProductoRepository;
import com.example.tiendas.servicio.PedidoService;

public class PedidoServiceTest {

	@Mock
	private PedidoRepository pedidoRepository;

	@Mock
	private DetallePedidoRepository detallePedidoRepository;

	@Mock
	private ProductoRepository productoRepository;

	@Mock
	private ClienteRepository clienteRepository;

	@InjectMocks
	private PedidoService pedidoService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
    public void testObtenerTodosLosPedidos() {
        // Configura el comportamiento del repositorio simulado
        when(pedidoRepository.findAll()).thenReturn(List.of(new Pedido(), new Pedido()));

        // Ejecuta el método que deseas probar
        List<Pedido> pedidos = pedidoService.obtenerTodosLosPedidos();

        // Verifica que el servicio haya llamado al repositorio
        verify(pedidoRepository).findAll();

        // Verifica que la lista de pedidos no esté vacía
        assertFalse(pedidos.isEmpty());
    }

	@Test
	public void testObtenerPedidoPorId() {
		// Configura el comportamiento del repositorio simulado
		Long pedidoId = 1L;
		Pedido pedido = new Pedido();
		pedido.setId(pedidoId);
		when(pedidoRepository.findById(pedidoId)).thenReturn(Optional.of(pedido));

		// Ejecuta el método que deseas probar
		Pedido resultado = pedidoService.obtenerPedidoPorId(pedidoId);

		// Verifica que el servicio haya llamado al repositorio
		verify(pedidoRepository).findById(pedidoId);

		// Verifica que el resultado no sea nulo
		assertNotNull(resultado);

		// Verifica que el ID del resultado coincida con el ID proporcionado
		assertEquals(pedidoId, resultado.getId());
	}

	@Test
	public void testCrearPedido() {
		// Configura el comportamiento del repositorio simulado
		Pedido pedido = new Pedido();
		when(pedidoRepository.save(pedido)).thenReturn(pedido);

		// Ejecuta el método que deseas probar
		Pedido resultado = pedidoService.crearPedido(pedido);

		// Verifica que el servicio haya llamado al repositorio
		verify(pedidoRepository).save(pedido);

		// Verifica que el resultado no sea nulo
		assertNotNull(resultado);
	}

	@Test
	public void testActualizarPedido() {
		// Configura el comportamiento del repositorio simulado
		Long pedidoId = 1L;
		Pedido pedidoExistente = new Pedido();
		pedidoExistente.setId(pedidoId);

		when(pedidoRepository.findById(pedidoId)).thenReturn(Optional.of(pedidoExistente));
		when(pedidoRepository.save(any(Pedido.class))).thenAnswer(invocation -> invocation.getArgument(0));

		// Ejecuta el método que deseas probar
		Pedido pedidoActualizado = new Pedido();
		pedidoActualizado.setId(pedidoId);

		Pedido resultado = pedidoService.actualizarPedido(pedidoId, pedidoActualizado);

		// Verifica que el servicio haya llamado al repositorio
		verify(pedidoRepository).findById(pedidoId);
		verify(pedidoRepository).save(pedidoExistente);

		// Verifica que el resultado no sea nulo
		assertNotNull(resultado);

		// Verifica que el ID del resultado coincida con el ID del pedido actualizado
		assertEquals(pedidoId, resultado.getId());
	}

	@Test
	public void testEliminarPedido() {
		// Configura el comportamiento del repositorio simulado
		Long pedidoId = 1L;
		Pedido pedido = new Pedido();
		pedido.setId(pedidoId);

		when(pedidoRepository.findById(pedidoId)).thenReturn(Optional.of(pedido));

		// Ejecuta el método que deseas probar
		pedidoService.eliminarPedido(pedidoId);

	}
}