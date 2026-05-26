package com.example.Catalogo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Catalogo.model.Catalogo;
import com.example.Catalogo.service.CatalogoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/catalogos")
public class CatalogoController {
    @Autowired
    private CatalogoService catalogoService;

    @GetMapping
    public List<Catalogo> listar() {
        return catalogoService.listar();
    }
    //BUSCAR POR ID
    @GetMapping("/buscarid{idCatalogo}")
    public ResponseEntity <Catalogo> buscarPorId(@PathVariable long idCatalogo) {
        Catalogo catal = catalogoService.buscarPorId(idCatalogo);
            if (catal == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(catal);
    }
    
    @GetMapping("/bucarnombre{nombre}")
    public ResponseEntity <Catalogo> buscarPorNombre (@PathVariable String nombre) {
        Catalogo catan = catalogoService.buscarPorNombre(nombre);
        if (catan == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(catan);
    }
    @PostMapping
    public ResponseEntity <Catalogo> agregarCatalogo(@Valid @RequestBody Catalogo catalogo) {
        Catalogo catologoNuevo  = catalogoService.agregarCatalogo(catalogo);
        return ResponseEntity.ok(catologoNuevo);
    }
    @PutMapping("modificar/{idCatalogo}")
    public ResponseEntity <Catalogo> actualizarCatalogo (@PathVariable Long idCatalogo,@Valid @RequestBody Catalogo catalogo) {
        Catalogo catalogoActualizar = catalogoService.actualizarCatalogo (idCatalogo , catalogo);
        if(catalogoActualizar == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(catalogoActualizar);
    }
    
    @DeleteMapping("eliminar/{idCatalogo}")
    public ResponseEntity <String> eliminarCatalogo(@PathVariable long idCatalogo){
        catalogoService.eliminarCatalogo(idCatalogo);
        return ResponseEntity.ok("Catalogo eliminado correctamente");
    }
}
