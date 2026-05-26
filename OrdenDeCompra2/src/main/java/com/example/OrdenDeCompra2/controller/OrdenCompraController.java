package com.example.OrdenDeCompra2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.OrdenDeCompra2.model.OrdenCompra;
import com.example.OrdenDeCompra2.service.OrdenCompraService;

@RestController
@RequestMapping("/ordenes")
public class OrdenCompraController {
    @Autowired
    private OrdenCompraService ordenService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearOrden(@RequestBody OrdenCompra orden) {
        try {
            ordenService.crearOrden(orden);
            return ResponseEntity.ok("Orden de compra creada exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al crear la orden: " + e.getMessage());
        }
    }


    @PutMapping("/{idOrden}/cancelar")
    public ResponseEntity<String> cancelarOrden(@PathVariable Long idOrden) {
        try {
            ordenService.cancelarOrden(idOrden);
            return ResponseEntity.ok("Orden cancelada correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{idOrden}/estado")
    public ResponseEntity<String> actualizarEstado(@PathVariable Long idOrden, @RequestParam String nuevoEstado) {
        try {
            ordenService.actualizarEstado(idOrden, nuevoEstado);
            return ResponseEntity.ok("Estado actualizado a: " + nuevoEstado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    
    @GetMapping("/{idOrden}")
    public ResponseEntity<OrdenCompra> getOrden(@PathVariable Long idOrden) {
        try {
            OrdenCompra orden = ordenService.getOrden(idOrden);
            return ResponseEntity.ok(orden);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
