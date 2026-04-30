package com.facturacion.service;

import com.facturacion.entity.Producto;
import com.facturacion.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findByProductoId(id);
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public Optional<Producto> actualizar(Long id, Producto productoActualizado) {
        return productoRepository.findByProductoId(id).map(producto -> {
            producto.setNombre(productoActualizado.getNombre());
            producto.setPrecio(productoActualizado.getPrecio());
            producto.setStock(productoActualizado.getStock());
            return productoRepository.save(producto);
        });
    }
}