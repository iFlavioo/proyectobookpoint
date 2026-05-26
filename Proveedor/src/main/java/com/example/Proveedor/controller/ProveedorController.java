package com.example.Proveedor.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.Proveedor.model.Proveedor;
import com.example.Proveedor.service.ProveedorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
    @Autowired ProveedorService proveedorService;
    
    @PostMapping()
    public Proveedor crearProveedor(@Valid @RequestBody Proveedor proveedor) {
        return proveedorService.crear(proveedor);
        
    }

    @GetMapping()
    public List<Proveedor> getProveedor() {
        return proveedorService.listar();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerPorId(@PathVariable Long id) {
    Proveedor proveedor = proveedorService.obtenerPorId(id);
    return ResponseEntity.ok(proveedor);
    }

    
    @PutMapping("{id}")
    public Proveedor putProveedor(@PathVariable Long id, @RequestBody Proveedor proveedor) {
        return proveedorService.actualizar(id,proveedor);
    }

    @DeleteMapping("{id}")
    public void deleteProveedor (@PathVariable Long id) {
    proveedorService.eliminar(id);
    }
}
