package com.bookpoint.returns_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bookpoint.returns_service.model.Devolucion;
import com.bookpoint.returns_service.service.DevolucionService;

@RestController
@RequestMapping("/api/devoluciones")
@CrossOrigin(origins = "*")
public class DevolucionController {

    @Autowired
    private DevolucionService service;

    @PostMapping
    public ResponseEntity<Devolucion> crearDevolucion(@RequestBody Devolucion devolucion) {
        return ResponseEntity.ok(service.registrarDevolucion(devolucion));
    }

    @GetMapping
    public ResponseEntity<List<Devolucion>> listarDevoluciones() {
        return ResponseEntity.ok(service.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Devolucion> buscarPorId(@PathVariable Long id) {
        return service.obtenerPorIde(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/venta/{ventaId}")
    public ResponseEntity<List<Devolucion>> buscarPorVenta(@PathVariable Long ventaId) {
        return ResponseEntity.ok(service.obtenerPorVentaId(ventaId));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Devolucion> cambiarEstado(@PathVariable Long id, @RequestParam String estado) {
        try {
            return ResponseEntity.ok(service.actualizarEstado(id, estado));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
