package com.example.tiendas.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tiendas.Excepciones.ClienteNotFoundException;
import com.example.tiendas.modulo.Cliente;
import com.example.tiendas.repositorio.ClienteRepository;

@Service
public class ClienteService {

	private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> obtenerTodosClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {
        if (clienteRepository.existsById(id)) {
            clienteActualizado.setId(id); // Asegura que el ID sea el mismo que el cliente existente
            return clienteRepository.save(clienteActualizado);
        } else {
            throw new ClienteNotFoundException("Cliente no encontrado");
        }
    }

    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
