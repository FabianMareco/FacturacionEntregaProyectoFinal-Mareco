package com.facturacion.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ComprobanteResponseDTO {
    private Long comprobanteId;
    private LocalDateTime fecha;
    private String clienteNombre;
    private List<LineaResponseDTO> lineas;
    private Double total;
    private Integer cantidadTotalProductos;

    public static class LineaResponseDTO {
        private String productoNombre;
        private Integer cantidad;
        private Double precioUnitario;
        private Double subtotal;

        public String getProductoNombre() { return productoNombre; }
        public void setProductoNombre(String productoNombre) { this.productoNombre = productoNombre; }

        public Integer getCantidad() { return cantidad; }
        public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

        public Double getPrecioUnitario() { return precioUnitario; }
        public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }

        public Double getSubtotal() { return subtotal; }
        public void setSubtotal(Double subtotal) { this.subtotal = subtotal; }
    }

    // Getters y Setters
    public Long getComprobanteId() { return comprobanteId; }
    public void setComprobanteId(Long comprobanteId) { this.comprobanteId = comprobanteId; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public String getClienteNombre() { return clienteNombre; }
    public void setClienteNombre(String clienteNombre) { this.clienteNombre = clienteNombre; }

    public List<LineaResponseDTO> getLineas() { return lineas; }
    public void setLineas(List<LineaResponseDTO> lineas) { this.lineas = lineas; }

    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }

    public Integer getCantidadTotalProductos() { return cantidadTotalProductos; }
    public void setCantidadTotalProductos(Integer cantidadTotalProductos) { this.cantidadTotalProductos = cantidadTotalProductos; }
}