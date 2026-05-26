package com.example.Catalogo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.Catalogo.model.Catalogo;
import com.example.Catalogo.model.Producto;
import com.example.Catalogo.service.ProductoService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping
        public List <Producto> ListarProdutos() {
        return productoService.listarTodoProductos();
    }
    @GetMapping("/bucaridproducto{idProducto}")
    public ResponseEntity<Producto> buscarPorIdProducto(@PathVariable Long idProducto) {
        Producto producto = productoService.busacarPorIdProductos(idProducto);
        if(producto == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }
    
    @GetMapping("/buscartipo")
    public List <Producto> buscaPorTipo (@RequestParam String tipo) {
        return productoService.buscarPorTipo(tipo);
    }
        @GetMapping("/disponibles")
    public List<Producto> listarDisponibles() {
        return productoService.ListarProductoDisponible();
    }
    @GetMapping("/buscarnombre")
    public List<Producto> buscarPorNombre(@RequestParam String nombreP) {
    return productoService.buscarPorNombreProductos(nombreP);
    }
    @PostMapping
    public ResponseEntity<Producto> agregar(@Valid @RequestBody Producto producto) {
        Producto productoNuevo = productoService.agregarProducto(producto);
        return ResponseEntity.ok(productoNuevo);
    }
    @PutMapping("/{idProducto}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long idProducto,@Valid @RequestBody Producto producto) {
        Producto actualizado = productoService.actualizaProducto(idProducto, producto);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }
    @DeleteMapping("eliminar/{idProducto}")
    public ResponseEntity<String> eliminar(@PathVariable Long idProducto) {
        productoService.eliminarProducto(idProducto);
        return ResponseEntity.ok("Producto eliminado correctamente");
    }
}

