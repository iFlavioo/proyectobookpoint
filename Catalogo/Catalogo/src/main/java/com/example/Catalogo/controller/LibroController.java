package com.example.Catalogo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Catalogo.model.Libro;
import com.example.Catalogo.service.LibroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/libros")

public class LibroController {
    @Autowired
        private LibroService libroService;
        @GetMapping
    public List<Libro> listarLibros() {
        return libroService.listarTodosLibros();
    }

    @GetMapping("/buscarid{idLibro}")
    public ResponseEntity<Libro> buscarPorIdLibro(@PathVariable Long idLibro) {
        Libro libro = libroService.buscarPorIdLibro(idLibro);
        if (libro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(libro);
    }
    @GetMapping("/buscarisbn{isbn}")
    public ResponseEntity<Libro> buscarPorIsbn(@PathVariable String isbn) {
        Libro libro = libroService.buscarPorIsbn(isbn);
        if (libro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(libro);
    }
    @GetMapping("/buscarautor{autor}")
    public List<Libro> buscarPorAutor(@PathVariable String autor) {
        return libroService.buscarPorAutor(autor);
    }
    @GetMapping("/buscareditorial{editorial}")
    public List<Libro> buscarPorEditorial(@PathVariable String editorial) {
        return libroService.buscarPorEditorial(editorial);
    }
    @PostMapping
    public ResponseEntity<Libro> agregarLibro(@Valid @RequestBody Libro libro) {
        Libro libroNuevo = libroService.agregarLibro(libro);
        return ResponseEntity.ok(libroNuevo);
    }
    @PutMapping("modificar/{idLibro}")
    public ResponseEntity<Libro> actualizarLibro(@PathVariable Long idLibro,@Valid @RequestBody Libro libro) {
        Libro actualizado = libroService.actualizarLibro(idLibro, libro);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }
    @DeleteMapping("eliminar/{idLibro}")
    public ResponseEntity<String> eliminarLibro(@PathVariable Long idLibro) {
        libroService.eliminarLibro(idLibro);
        return ResponseEntity.ok("Libro eliminado correctamente");
    }
}
