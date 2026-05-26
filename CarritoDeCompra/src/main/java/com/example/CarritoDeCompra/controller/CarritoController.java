package com.example.CarritoDeCompra.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.CarritoDeCompra.model.CarritoDeCompra;
import com.example.CarritoDeCompra.service.CarritoDeCompraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping

public class CarritoController {
    @Autowired
    private CarritoDeCompraService carritoService;

    @GetMapping("/{usuarioId}")
    public ResponseEntity<CarritoDeCompra> obtenerCarrito(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(carritoService.obtenerOCrearCarrito(usuarioId));
    }

    @PostMapping("/{usuarioId}/agregar")
    public ResponseEntity<String> agregar(@PathVariable Long usuarioId, 
                                        @RequestParam Long idProducto, 
                                        @RequestParam double precio) {
        carritoService.agregarProducto(usuarioId, idProducto, precio);
        return ResponseEntity.ok("Producto agregado con éxito");
    }

    @DeleteMapping("/{usuarioId}/eliminar")
    public ResponseEntity<String> eliminar(@PathVariable Long usuarioId, 
                                        @RequestParam Long idProducto,
                                        @RequestParam double precio) {
        carritoService.eliminarProducto(usuarioId, idProducto, precio);
        return ResponseEntity.ok("Producto eliminado con éxito");
    }

    @PostMapping("/{usuarioId}/vaciar")
    public ResponseEntity<String> vaciar(@PathVariable Long usuarioId) {
        carritoService.vaciarCarrito(usuarioId);
        return ResponseEntity.ok("El carrito ha sido vaciado");
    }

    @PostMapping("/{usuarioId}/descuento")
    public ResponseEntity<String> aplicarDescuento(@PathVariable Long usuarioId, 
                                                @RequestParam String codigo) {
        carritoService.aplicarDescuento(usuarioId, codigo);
        return ResponseEntity.ok("Descuento procesado");
    }
    
}
