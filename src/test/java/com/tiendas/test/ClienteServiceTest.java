package com.tiendas.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.tiendas.Excepciones.ClienteNotFoundException;
import com.example.tiendas.modulo.Cliente;
import com.example.tiendas.repositorio.ClienteRepository;
import com.example.tiendas.servicio.ClienteService;

public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerTodosClientes() {
        // Configura el comportamiento del repositorio simulado
        when(clienteRepository.findAll()).thenReturn(List.of(new Cliente(), new Cliente()));

        // Ejecuta el método que deseas probar
        List<Cliente> clientes = clienteService.obtenerTodosClientes();

        // Verifica que el servicio haya llamado al repositorio
        verify(clienteRepository).findAll();

        // Verifica que la lista de clientes no esté vacía
        assertFalse(clientes.isEmpty());
    }

    @Test
    public void testObtenerClientePorIdExistente() {
        // ID del cliente existente
        Long clienteId = 1L;

        // Configura el comportamiento del repositorio simulado
        Cliente clienteExistente = new Cliente();
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(clienteExistente));

        // Ejecuta el método que deseas probar
        Optional<Cliente> cliente = clienteService.obtenerClientePorId(clienteId);

        // Verifica que el servicio haya llamado al repositorio
        verify(clienteRepository).findById(clienteId);

        // Verifica que se haya devuelto un cliente
        assertTrue(cliente.isPresent());
    }

    @Test
    public void testObtenerClientePorIdNoExistente() {
        // ID de cliente no existente
        Long clienteId = 1L;

        // Comportamiento del repositorio simulado
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.empty());

        // lanza una excepción
        assertThrows(ClienteNotFoundException.class, () -> clienteService.obtenerClientePorId(clienteId));
    }
}
