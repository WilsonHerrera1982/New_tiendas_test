package com.tiendas.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.tiendas.modulo.Producto;
import com.example.tiendas.modulo.Tienda;
import com.example.tiendas.repositorio.ProductoRepository;
import com.example.tiendas.servicio.ProductoService;

import jakarta.persistence.EntityManager;

public class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerTodosLosProductos() {
        // Configura el comportamiento del repositorio simulado
        when(productoRepository.findAll()).thenReturn(List.of(new Producto(), new Producto()));

        // Ejecuta el método que deseas probar
        List<Producto> productos = productoService.obtenerTodosLosProductos();

        // Verifica que el servicio haya llamado al repositorio
        verify(productoRepository).findAll();

        // Verifica que la lista de productos no esté vacía
        assertFalse(productos.isEmpty());
    }

    @Test
    public void testObtenerProductoPorId() {
        // Configura el comportamiento del repositorio simulado
        Long productoId = 1L;
        Producto producto = new Producto();
        producto.setId(productoId);
        Tienda tienda = new Tienda();
        tienda.setId(2L);
        tienda.setNombre("Tienda de prueba");
        tienda.setDireccion("Dirección de prueba");
        tienda.setTelefono("123456789");
        producto.setTienda(tienda);

        when(productoRepository.findById(productoId)).thenReturn(Optional.of(producto));

        // Ejecuta el método que deseas probar
        Producto resultado = productoService.obtenerProductoPorId(productoId);

        // Verifica que el servicio haya llamado al repositorio
        verify(productoRepository).findById(productoId);

        // Verifica que el resultado no sea nulo
        assertNotNull(resultado);

        // Verifica que la tienda en el resultado no sea nula
        assertNotNull(resultado.getTienda());

        // Verifica que el ID del resultado coincida con el ID proporcionado
        assertEquals(productoId, resultado.getId());
    }

    @Test
    public void testCrearProducto() {
        // Configura el comportamiento del repositorio simulado
        Producto producto = new Producto();
        when(productoRepository.save(producto)).thenReturn(producto);

        // Ejecuta el método que deseas probar
        Producto resultado = productoService.crearProducto(producto);

        // Verifica que el servicio haya llamado al repositorio
       
    }
}

