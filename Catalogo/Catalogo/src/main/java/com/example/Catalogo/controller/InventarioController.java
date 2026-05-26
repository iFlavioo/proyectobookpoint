package com.example.Catalogo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Catalogo.model.Inventario;
import com.example.Catalogo.service.InventarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/inventarios")
public class InventarioController {
    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public List<Inventario> listarInventario() {
        return inventarioService.listarTodoInventario();
    }
    @GetMapping("/buscarid{idInventario}")
    public ResponseEntity<Inventario> buscarPorIdInventario(@PathVariable Long idInventario) {
        Inventario inv = inventarioService.buscarPorIdInventario(idInventario);
        if (inv == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(inv);
    }
    @GetMapping("/buscarproducto{productoId}")
    public ResponseEntity<Inventario> buscarPorProductoId(@PathVariable Long productoId) {
        Inventario inv = inventarioService.buscarPorProductoId(productoId);
        if (inv == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(inv);
    }
    @GetMapping("/buscarsucursal{sucursal}")
    public List<Inventario> buscarPorSucursal(@PathVariable String sucursal) {
        return inventarioService.buscarPorSucursal(sucursal);
    }
    @GetMapping("/stockbajo")
    public List<Inventario> listarStockBajo() {
        return inventarioService.listarStockBajo();
    }
    @PostMapping
    public ResponseEntity<Inventario> agregarInventario(@Valid @RequestBody Inventario inventario) {
        return ResponseEntity.ok(inventarioService.agregarInventario(inventario));
    }
    @PutMapping("modificar/{idInventario}")
    public ResponseEntity<Inventario> actualizarInventario(@PathVariable Long idInventario,@Valid @RequestBody Inventario inventario) {
        Inventario actualizado = inventarioService.actualizarInventario(idInventario, inventario);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }
    @PatchMapping("agregarstock/{idInventario}")
    public ResponseEntity<Inventario> agregarStock(@PathVariable Long idInventario,@RequestParam int cantidad) {
        Inventario actualizado = inventarioService.agregarStock(idInventario, cantidad);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }
    @PatchMapping("descontarstock/{idInventario}")
    public ResponseEntity<Inventario> descontarStock(@PathVariable Long idInventario,@RequestParam int cantidad) {
        Inventario actualizado = inventarioService.descontarStock(idInventario, cantidad);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }
    @DeleteMapping("eliminar/{idInventario}")
    public ResponseEntity<String> eliminarInventario(@PathVariable Long idInventario) {
        inventarioService.eliminarInventario(idInventario);
        return ResponseEntity.ok("Inventario eliminado correctamente");
    }
}

