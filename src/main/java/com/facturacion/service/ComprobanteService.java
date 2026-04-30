package com.facturacion.service;

import com.facturacion.dto.ComprobanteRequestDTO;
import com.facturacion.dto.ComprobanteResponseDTO;
import com.facturacion.entity.*;
import com.facturacion.repository.ComprobanteRepository;
import com.facturacion.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComprobanteService {

    @Autowired
    private ComprobanteRepository comprobanteRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProductoService productoService;

    @Transactional
    public ComprobanteResponseDTO crearComprobante(ComprobanteRequestDTO request) throws Exception {
        // Validar cliente
        Optional<Cliente> clienteOpt = clienteService.buscarPorId(request.getCliente().getClienteId());
        if (!clienteOpt.isPresent()) {
            throw new Exception("Cliente no existe");
        }
        Cliente cliente = clienteOpt.get();

        List<LineaComprobante> lineas = new ArrayList<>();
        Double total = 0.0;
        Integer cantidadTotal = 0;

        for (ComprobanteRequestDTO.LineaRequest lineaReq : request.getLineas()) {
            // Validar producto
            Optional<Producto> productoOpt = productoService.buscarPorId(lineaReq.getProducto().getProductoId());
            if (!productoOpt.isPresent()) {
                throw new Exception("Producto con ID " + lineaReq.getProducto().getProductoId() + " no existe");
            }
            Producto producto = productoOpt.get();

            // Validar cantidad y stock
            if (lineaReq.getCantidad() <= 0) {
                throw new Exception("Cantidad inválida para producto " + producto.getNombre());
            }
            if (lineaReq.getCantidad() > producto.getStock()) {
                throw new Exception("Stock insuficiente para " + producto.getNombre() +
                        ". Stock disponible: " + producto.getStock());
            }

            // Reducir stock
            producto.setStock(producto.getStock() - lineaReq.getCantidad());
            productoService.guardar(producto);

            // Calcular línea
            Double precioUnitario = producto.getPrecio();
            Double subtotal = precioUnitario * lineaReq.getCantidad();
            total += subtotal;
            cantidadTotal += lineaReq.getCantidad();

            LineaComprobante linea = new LineaComprobante();
            linea.setCantidad(lineaReq.getCantidad());
            linea.setProducto(producto);
            linea.setPrecioUnitario(precioUnitario);
            lineas.add(linea);
        }

        // Obtener fecha desde API externa con fallback
        LocalDateTime fecha = DateUtil.obtenerFechaActual();

        Comprobante comprobante = new Comprobante();
        comprobante.setFecha(fecha);
        comprobante.setCliente(cliente);
        comprobante.setLineas(lineas);
        comprobante.setTotal(total);

        for (LineaComprobante linea : lineas) {
            linea.setComprobante(comprobante);
        }

        Comprobante guardado = comprobanteRepository.save(comprobante);

        // Construir respuesta DTO
        ComprobanteResponseDTO response = new ComprobanteResponseDTO();
        response.setComprobanteId(guardado.getComprobanteId());
        response.setFecha(guardado.getFecha());
        response.setClienteNombre(guardado.getCliente().getNombre());
        response.setTotal(guardado.getTotal());
        response.setCantidadTotalProductos(cantidadTotal);

        List<ComprobanteResponseDTO.LineaResponseDTO> lineasDTO = new ArrayList<>();
        for (LineaComprobante linea : guardado.getLineas()) {
            ComprobanteResponseDTO.LineaResponseDTO lineaDTO = new ComprobanteResponseDTO.LineaResponseDTO();
            lineaDTO.setProductoNombre(linea.getProducto().getNombre());
            lineaDTO.setCantidad(linea.getCantidad());
            lineaDTO.setPrecioUnitario(linea.getPrecioUnitario());
            lineaDTO.setSubtotal(linea.getPrecioUnitario() * linea.getCantidad());
            lineasDTO.add(lineaDTO);
        }
        response.setLineas(lineasDTO);

        return response;
    }
}