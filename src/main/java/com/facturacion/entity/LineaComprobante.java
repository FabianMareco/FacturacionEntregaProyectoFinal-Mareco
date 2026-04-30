package com.facturacion.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lineas_comprobante")
public class LineaComprobante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lineaId;

    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private Double precioUnitario;

    @ManyToOne
    @JoinColumn(name = "comprobante_id")
    private Comprobante comprobante;

    public LineaComprobante() {}

    public LineaComprobante(Integer cantidad, Producto producto, Double precioUnitario) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.precioUnitario = precioUnitario;
    }

    public Long getLineaId() { return lineaId; }
    public void setLineaId(Long lineaId) { this.lineaId = lineaId; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }

    public Comprobante getComprobante() { return comprobante; }
    public void setComprobante(Comprobante comprobante) { this.comprobante = comprobante; }
}