package com.facturacion.controller;

import com.facturacion.dto.ComprobanteRequestDTO;
import com.facturacion.dto.ComprobanteResponseDTO;
import com.facturacion.service.ComprobanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comprobantes")
public class ComprobanteController {

    @Autowired
    private ComprobanteService comprobanteService;

    @PostMapping
    public ResponseEntity<?> crearComprobante(@RequestBody ComprobanteRequestDTO request) {
        try {
            ComprobanteResponseDTO response = comprobanteService.crearComprobante(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}