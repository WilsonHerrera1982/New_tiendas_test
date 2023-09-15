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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tiendas.modulo.Pedido;
import com.example.tiendas.modulo.PedidoRequest;
import com.example.tiendas.servicio.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	@Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoService.obtenerTodosLosPedidos();
    }

    @GetMapping("/{id}")
    public Pedido obtenerPedidoPorId(@PathVariable Long id) {
        return pedidoService.obtenerPedidoPorId(id);
    }

    @PostMapping
    public Pedido crearPedido(@RequestBody PedidoRequest pedido) {
        return pedidoService.realizarPedido(pedido);
    }

    @PutMapping("/{id}")
    public Pedido actualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        return pedidoService.actualizarPedido(id, pedido);
    }

    @DeleteMapping("/{id}")
    public void eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
    }

    @PostMapping("/realizar-pedido")
    public Pedido realizarPedido(@RequestBody PedidoRequest pedidoRequest) {
        return pedidoService.realizarPedido(pedidoRequest);
    }

    @GetMapping("/por-cliente")
    public List<Pedido> obtenerPedidosPorClienteEnRangoDeFechas(
            @RequestParam Long clienteId,
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {
        return pedidoService.obtenerPedidosPorClienteEnRangoDeFechas(clienteId, fechaInicio, fechaFin);
    }
}
