package com.bookpoint.review_service.controller;

import com.bookpoint.review_service.model.Resena;
import com.bookpoint.review_service.service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @GetMapping
    public List<Resena> listarTodas() {
        return resenaService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resena> buscarPorId(@PathVariable Long id) {
        return resenaService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/producto/{productoId}")
    public List<Resena> buscarPorProducto(@PathVariable Long productoId) {
        return resenaService.buscarPorProducto(productoId);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Resena> buscarPorUsuario(@PathVariable Long usuarioId) {
        return resenaService.buscarPorUsuario(usuarioId);
    }

    @PostMapping
    public ResponseEntity<Resena> agregar(@RequestBody Resena resena) {
        try {
            return ResponseEntity.ok(resenaService.agregar(resena));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            resenaService.eliminar(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
