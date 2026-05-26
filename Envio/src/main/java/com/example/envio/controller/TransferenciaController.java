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

import com.example.envio.model.Transferencia;
import com.example.envio.service.TransferenciaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController {
    @Autowired
    private TransferenciaService transferenciaService;

    @GetMapping
    public List<Transferencia> listarTransferencias() {
        return transferenciaService.listarTodasTransferencias();
    }
    @GetMapping("/buscarid{idTransferencia}")
    public ResponseEntity<Transferencia> buscarPorId(@PathVariable Long idTransferencia) {
        Transferencia transferencia = transferenciaService.buscarPorIdTransferencia(idTransferencia);
        if (transferencia == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transferencia);
    }
    @GetMapping("/buscarsucursal{sucursalOrigen}")
    public List<Transferencia> buscarPorOrigen(@PathVariable Long sucursalOrigen) {
        return transferenciaService.buscarPorOrigen(sucursalOrigen);
    }
    @GetMapping("/buscarestado")
    public List<Transferencia> buscarPorEstado(@RequestParam String estado) {
        return transferenciaService.buscarPorEstado(estado);
    }
    @PostMapping
    public ResponseEntity<Transferencia> solicitarTransferencia(@Valid @RequestBody Transferencia transferencia) {
        return ResponseEntity.ok(transferenciaService.solicitarTransferencia(transferencia));
    }
    @PatchMapping("aprobar/{idTransferencia}")
    public ResponseEntity<Transferencia> aprobarTransferencia(@PathVariable Long idTransferencia) {
        Transferencia aprobada = transferenciaService.aprobarTransferencia(idTransferencia);
        if (aprobada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(aprobada);
    }
    @PatchMapping("rechazar/{idTransferencia}")
    public ResponseEntity<Transferencia> rechazarTransferencia(@PathVariable Long idTransferencia) {
        Transferencia rechazada = transferenciaService.rechazarTransferencia(idTransferencia);
        if (rechazada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rechazada);
    }
    @DeleteMapping("eliminar/{idTransferencia}")
    public ResponseEntity<String> eliminarTransferencia(@PathVariable Long idTransferencia) {
        transferenciaService.eliminarTransferencia(idTransferencia);
        return ResponseEntity.ok("Transferencia eliminada correctamente");
    }
}