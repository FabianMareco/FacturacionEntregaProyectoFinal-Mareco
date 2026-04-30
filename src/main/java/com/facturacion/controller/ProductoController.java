package com.facturacion.controller;

import com.facturacion.entity.Producto;
import com.facturacion.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> listar() {
        return ResponseEntity.ok(productoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Optional<Producto> producto = productoService.buscarPorId(id);
        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Producto producto) {
        try {
            Producto nuevo = productoService.guardar(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al crear producto: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Producto producto) {
        Optional<Producto> actualizado = productoService.actualizar(id, producto);
        if (actualizado.isPresent()) {
            return ResponseEntity.ok(actualizado.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }
}