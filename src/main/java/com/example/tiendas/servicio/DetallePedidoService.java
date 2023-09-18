package com.example.tiendas.servicio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tiendas.modulo.DetallePedido;
import com.example.tiendas.modulo.DetallePedidoRequest;
import com.example.tiendas.repositorio.DetallePedidoRepository;

@Service
public class DetallePedidoService {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    public DetallePedido obtenerDetallePedidoPorId(Long id) {
        return detallePedidoRepository.findById(id).orElse(null);
    }

    public List<DetallePedido> obtenerDetallesPorPedidoId(Long pedidoId) {
    	List<DetallePedido> det=new ArrayList<>();
        det= detallePedidoRepository.findByPedidoId(pedidoId);
        return det;
    }

    public DetallePedido crearDetallePedido(DetallePedidoRequest detallePedidoRequest) {
        DetallePedido detallePedido = new DetallePedido();
        // Configura los campos de detallePedido según detallePedidoRequest
        // ...
        return detallePedidoRepository.save(detallePedido);
    }

    public DetallePedido actualizarDetallePedido(Long id, DetallePedido detallePedido) {
        if (detallePedidoRepository.existsById(id)) {
            detallePedido.setId(id);
            // Configura los campos de detallePedido según los datos proporcionados
            // ...
            return detallePedidoRepository.save(detallePedido);
        } else {
            return null; // El detalle de pedido con ese ID no existe
        }
    }

    public void eliminarDetallePedido(Long id) {
        detallePedidoRepository.deleteById(id);
    }
}
