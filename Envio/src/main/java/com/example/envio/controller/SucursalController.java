package com.example.envio.controller;

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

import com.example.envio.model.Sucursal;
import com.example.envio.service.SucursalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public List<Sucursal> listarSucursales() {
        return sucursalService.listarTodasSucursales();
    }
    @GetMapping("/buscarid{id}")
    public ResponseEntity<Sucursal> buscarPorIdSucursal(@PathVariable Long id) {
        Sucursal sucursal = sucursalService.buscarPorIdSucursal(id);
        if (sucursal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sucursal);
    }
    @GetMapping("/buscarnombre{nombre}")
    public ResponseEntity<Sucursal> buscarPorNombre(@PathVariable String nombre) {
        Sucursal sucursal = sucursalService.buscarPorNombre(nombre);
        if (sucursal == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sucursal);
    }
    @GetMapping("/buscarciudad")
    public List<Sucursal> buscarPorCiudad(@RequestParam String ciudad) {
        return sucursalService.buscarPorCiudad(ciudad);
    }
    @PostMapping
    public ResponseEntity<Sucursal> registrarSucursal(@Valid @RequestBody Sucursal sucursal) {
        return ResponseEntity.ok(sucursalService.registrarSucursal(sucursal));
    }
    @PutMapping("modificar/{id}")
    public ResponseEntity<Sucursal> actualizarSucursal(@PathVariable Long id,@Valid @RequestBody Sucursal sucursal) {
        Sucursal actualizada = sucursalService.actualizarSucursal(id, sucursal);
        if (actualizada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizada);
    }
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<String> eliminarSucursal(@PathVariable Long id) {
        sucursalService.eliminarSucursal(id);
        return ResponseEntity.ok("Sucursal eliminada correctamente");
    }
}