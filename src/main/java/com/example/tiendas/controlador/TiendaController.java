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

import com.example.tiendas.dto.TiendaDto;
import com.example.tiendas.modulo.Tienda;
import com.example.tiendas.servicio.TiendaService;

@RestController
@RequestMapping("/api/tiendas")
public class TiendaController {

	@Autowired
    private TiendaService tiendaService;

    @GetMapping
    public List<TiendaDto> obtenerTodasLasTiendas() {
        return tiendaService.obtenerTodasLasTiendas();
    }

    @GetMapping("/{id}")
    public Tienda obtenerTiendaPorId(@PathVariable Long id) {
        return tiendaService.obtenerTiendaPorId(id);
    }

    @PostMapping
    public Tienda crearTienda(@RequestBody Tienda tienda) {
        return tiendaService.crearTienda(tienda);
    }

    @PutMapping("/{id}")
    public Tienda actualizarTienda(@PathVariable Long id, @RequestBody Tienda tienda) {
        return tiendaService.actualizarTienda(id, tienda);
    }

    @DeleteMapping("/{id}")
    public void eliminarTienda(@PathVariable Long id) {
        tiendaService.eliminarTienda(id);
    }
    
    @GetMapping("/{id}/productos")
    public TiendaDto  obtenerTiendaConProductos(@PathVariable Long id) {
        return tiendaService.obtenerTiendaConProductos(id);
    }
}
