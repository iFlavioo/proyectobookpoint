package com.example.envio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.envio.model.Envio;
import com.example.envio.service.EnvioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;
    @GetMapping
    public List<Envio> listarEnvios() {
        return envioService.listarTodosEnvios();
    }
    @GetMapping("/buscarid/{idEnvio}")
    public ResponseEntity<Envio> buscarPorIdEnvio(@PathVariable Long idEnvio) {
        Envio envio = envioService.buscarPorIdEnvio(idEnvio);
        if (envio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(envio);
    }
    @GetMapping("/buscarventa/{ventaId}")
    public ResponseEntity<Envio> buscarPorVentaId(@PathVariable Long ventaId) {
        Envio envio = envioService.buscarPorVentaId(ventaId);
        if (envio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(envio);
    }
    @GetMapping("/buscarestado")
    public List<Envio> buscarPorEstado(@RequestParam String estado) {
        return envioService.buscarPorEstado(estado);
    }
    @GetMapping("/seguir/{idEnvio}")
    public ResponseEntity<Envio> seguirEnvio(@PathVariable Long idEnvio) {
        Envio envio = envioService.seguirEnvio(idEnvio);
        if (envio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(envio);
    }
    @PostMapping
    public ResponseEntity<Envio> crearEnvio(@Valid @RequestBody Envio envio) {
        return ResponseEntity.ok(envioService.crearEnvio(envio));
    }
    @PatchMapping("/actualizarestado/{idEnvio}")
    public ResponseEntity<Envio> actualizarEstado(@PathVariable Long idEnvio, @RequestParam String estado) {
        Envio actualizado = envioService.actualizarEstado(idEnvio, estado);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }
    @PatchMapping("/cancelar/{idEnvio}")
    public ResponseEntity<String> cancelarEnvio(@PathVariable Long idEnvio) {
        envioService.cancelarEnvio(idEnvio);
        return ResponseEntity.ok("Envio cancelado correctamente");
    }
    @DeleteMapping("/eliminar/{idEnvio}")
    public ResponseEntity<String> eliminarEnvio(@PathVariable Long idEnvio) {
        envioService.eliminarEnvio(idEnvio);
        return ResponseEntity.ok("Envio eliminado correctamente");
    }
}