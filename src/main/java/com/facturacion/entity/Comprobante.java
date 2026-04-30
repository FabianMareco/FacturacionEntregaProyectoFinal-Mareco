package com.facturacion.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comprobantes")
public class Comprobante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comprobanteId;

    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "comprobante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaComprobante> lineas = new ArrayList<>();

    private Double total;

    public Comprobante() {}

    public Comprobante(LocalDateTime fecha, Cliente cliente, List<LineaComprobante> lineas, Double total) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.lineas = lineas;
        this.total = total;
    }

    public Long getComprobanteId() { return comprobanteId; }
    public void setComprobanteId(Long comprobanteId) { this.comprobanteId = comprobanteId; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<LineaComprobante> getLineas() { return lineas; }
    public void setLineas(List<LineaComprobante> lineas) { this.lineas = lineas; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
}