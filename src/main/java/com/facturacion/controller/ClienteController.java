package com.facturacion.controller;

import com.facturacion.entity.Cliente;
import com.facturacion.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.buscarPorId(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Cliente cliente) {
        try {
            Cliente nuevo = clienteService.guardar(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al crear cliente: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> actualizado = clienteService.actualizar(id, cliente);
        if (actualizado.isPresent()) {
            return ResponseEntity.ok(actualizado.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
    }
}