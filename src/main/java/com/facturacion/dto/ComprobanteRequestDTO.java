package com.facturacion.dto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

public class ComprobanteRequestDTO {
    @NotNull
    private ClienteInfo cliente;

    @NotNull
    private List<LineaRequest> lineas;

    public static class ClienteInfo {
        private Long clienteId;
        public Long getClienteId() { return clienteId; }
        public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    }

    public static class LineaRequest {
        private Integer cantidad;
        private ProductoInfo producto;

        public Integer getCantidad() { return cantidad; }
        public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

        public ProductoInfo getProducto() { return producto; }
        public void setProducto(ProductoInfo producto) { this.producto = producto; }
    }

    public static class ProductoInfo {
        private Long productoId;
        public Long getProductoId() { return productoId; }
        public void setProductoId(Long productoId) { this.productoId = productoId; }
    }

    public ClienteInfo getCliente() { return cliente; }
    public void setCliente(ClienteInfo cliente) { this.cliente = cliente; }

    public List<LineaRequest> getLineas() { return lineas; }
    public void setLineas(List<LineaRequest> lineas) { this.lineas = lineas; }
}