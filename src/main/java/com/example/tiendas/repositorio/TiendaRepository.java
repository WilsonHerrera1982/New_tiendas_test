package com.example.tiendas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tiendas.modulo.Tienda;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Long>  {

}
