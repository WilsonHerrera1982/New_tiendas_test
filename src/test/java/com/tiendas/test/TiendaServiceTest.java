package com.tiendas.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

import com.example.tiendas.dto.TiendaDto;
import com.example.tiendas.modulo.Tienda;
import com.example.tiendas.repositorio.TiendaRepository;
import com.example.tiendas.servicio.TiendaService;

public class TiendaServiceTest {

    @Mock
    private TiendaRepository tiendaRepository;

    @InjectMocks
    private TiendaService tiendaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerTodasTiendas() {
        // Configura el comportamiento del repositorio simulado
        when(tiendaRepository.findAll()).thenReturn(List.of(new Tienda(), new Tienda()));

        // Ejecuta el método que deseas probar
        List<TiendaDto> tiendas = tiendaService.obtenerTodasLasTiendas();

        // Verifica que el servicio haya llamado al repositorio
        verify(tiendaRepository).findAll();

        // Verifica que la lista de tiendas no esté vacía
        assertFalse(tiendas.isEmpty());
    }

    @Test
    public void testObtenerTiendaPorId() {
        // Configura el comportamiento del repositorio simulado
        Long tiendaId = 1L;
        Tienda tienda = new Tienda();
        tienda.setId(tiendaId);
        when(tiendaRepository.findById(tiendaId)).thenReturn(Optional.of(tienda));

        // Ejecuta el método que deseas probar
        Optional<TiendaDto> resultado = tiendaService.obtenerTiendaTestPorId(tiendaId);

        // Verifica que el servicio haya llamado al repositorio
        verify(tiendaRepository).findById(tiendaId);

        // Verifica que el resultado no sea nulo
        assertTrue(resultado.isPresent());

        // Verifica que el resultado sea la tienda esperada
        assertEquals(tiendaId, resultado.get().getId());
    }

    @Test
    public void testCrearTienda() {
        // Configura el comportamiento del repositorio simulado
        Tienda tienda = new Tienda();
        when(tiendaRepository.save(tienda)).thenReturn(tienda);

        // Ejecuta el método que deseas probar
        Tienda resultado = tiendaService.crearTienda(tienda);

        // Verifica que el servicio haya llamado al repositorio
        verify(tiendaRepository).save(tienda);

        // Verifica que el resultado no sea nulo
        assertNotNull(resultado);
    }

    @Test
    public void testActualizarTienda() {
        // Configura el comportamiento del repositorio simulado
        Long tiendaId = 1L;
        Tienda tiendaExistente = new Tienda();
        tiendaExistente.setId(tiendaId);
        when(tiendaRepository.existsById(tiendaId)).thenReturn(true);
        when(tiendaRepository.save(any(Tienda.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Ejecuta el método que deseas probar
        Tienda tiendaActualizada = new Tienda();
        tiendaActualizada.setId(tiendaId);
        Tienda resultado = tiendaService.actualizarTienda(tiendaId, tiendaActualizada);

        // Verifica que el servicio haya llamado al repositorio
        verify(tiendaRepository).existsById(tiendaId);
        verify(tiendaRepository).save(tiendaActualizada);

        // Verifica que el resultado no sea nulo
        assertNotNull(resultado);

        // Verifica que el ID del resultado sea el mismo que el ID de la tienda actualizada
        assertEquals(tiendaId, resultado.getId());
    }

    @Test
    public void testEliminarTienda() {
        // Configura el comportamiento del repositorio simulado
        Long tiendaId = 1L;
        Tienda tienda = new Tienda();
        tienda.setId(tiendaId);
        when(tiendaRepository.findById(tiendaId)).thenReturn(Optional.of(tienda));

        // Ejecuta el método que deseas probar
        tiendaService.eliminarTienda(tiendaId);

        // Verifica que el servicio haya llamado al repositorio
        verify(tiendaRepository).findById(tiendaId);
        //verify(tiendaRepository).
        
    }
}
