package com.example.tiendas.modulo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date fecha;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	private String estado;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DetallePedido> detalles = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void addDetalle(DetallePedido detalle) {
		detalle.setPedido(this);
		detalles.add(detalle);
	}

	public void removeDetalle(DetallePedido detalle) {
		detalle.setPedido(null);
		detalles.remove(detalle);
	}

	public void setDetalles(List<DetallePedido> detalles) {
        // Asigna la lista de detalles al pedido
        this.detalles.clear(); // Borra los detalles existentes
        if (detalles != null) {
            detalles.forEach(this::addDetalle); // Agrega los nuevos detalles
        }
    }
}
