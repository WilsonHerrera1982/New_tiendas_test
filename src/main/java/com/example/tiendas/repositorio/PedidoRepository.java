package com.example.tiendas.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tiendas.modulo.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	List<Pedido> findByClienteIdAndFechaBetween(Long clienteId, Date inicio, Date fin);
}
