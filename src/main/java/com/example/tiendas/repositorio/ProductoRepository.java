package com.example.tiendas.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tiendas.modulo.Producto;
import com.example.tiendas.modulo.Tienda;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	 @EntityGraph(attributePaths = "tienda")
	    Optional<Producto> findById(Long id);
 List<Producto> findByTienda(Tienda tienda);
}
