package com.example.tiendas.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tiendas.Excepciones.ClienteNotFoundException;
import com.example.tiendas.modulo.Cliente;
import com.example.tiendas.servicio.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> obtenerTodosClientes() {
        return clienteService.obtenerTodosClientes();
    }

    @GetMapping("/{id}")
    public Cliente obtenerClientePorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.obtenerClientePorId(id);
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            throw new ClienteNotFoundException("Cliente no encontrado");
        }
    }

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.crearCliente(cliente);
    }

    @PutMapping("/{id}")
    public Cliente actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteActualizado) {
        return clienteService.actualizarCliente(id, clienteActualizado);
    }

    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
    }
}
