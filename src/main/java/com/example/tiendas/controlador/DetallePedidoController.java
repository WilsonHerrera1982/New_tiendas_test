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
import org.springframework.web.bind.annotation.RestController;

import com.example.tiendas.modulo.DetallePedido;
import com.example.tiendas.modulo.DetallePedidoRequest;
import com.example.tiendas.servicio.DetallePedidoService;

@RestController
@RequestMapping("/api/detalles-pedido")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoService detallePedidoService;

    @GetMapping("/{id}")
    public DetallePedido obtenerDetallePedidoPorId(@PathVariable Long id) {
        return detallePedidoService.obtenerDetallePedidoPorId(id);
    }

    @GetMapping("/por-pedido/{pedidoId}")
    public List<DetallePedido> obtenerDetallesPorPedidoId(@PathVariable Long pedidoId) {
        return detallePedidoService.obtenerDetallesPorPedidoId(pedidoId);
    }

    @PostMapping
    public DetallePedido crearDetallePedido(@RequestBody DetallePedidoRequest detallePedidoRequest) {
        return detallePedidoService.crearDetallePedido(detallePedidoRequest);
    }

    @PutMapping("/{id}")
    public DetallePedido actualizarDetallePedido(@PathVariable Long id, @RequestBody DetallePedido detallePedido) {
        return detallePedidoService.actualizarDetallePedido(id, detallePedido);
    }

    @DeleteMapping("/{id}")
    public void eliminarDetallePedido(@PathVariable Long id) {
        detallePedidoService.eliminarDetallePedido(id);
    }
}

