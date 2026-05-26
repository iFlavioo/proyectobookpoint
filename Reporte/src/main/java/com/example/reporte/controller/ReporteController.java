package com.example.reporte.controller;

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

import com.example.reporte.model.Reporte;
import com.example.reporte.service.ReporteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;
    @GetMapping
    public List<Reporte> listarReportes() {
        return reporteService.listarTodosReportes();
    }
    @GetMapping("/buscarid{idReporte}")
    public ResponseEntity<Reporte> buscarPorIdReporte(@PathVariable Long idReporte) {
        Reporte reporte = reporteService.buscarPorIdReporte(idReporte);
        if (reporte == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(reporte);
    }
    @GetMapping("/buscardestinatario{destinatarioId}")
    public List<Reporte> buscarPorDestinatario(@PathVariable Long destinatarioId) {
        return reporteService.buscarPorDestinatario(destinatarioId);
    }
    @GetMapping("/buscarsucursal{sucursalId}")
    public List<Reporte> buscarPorSucursal(@PathVariable Long sucursalId) {
        return reporteService.buscarPorSucursal(sucursalId);
    }
    @GetMapping("/ventacategoria")
    public List<Reporte> reporteVentaCategoria() {
        return reporteService.reporteVentaCategoria();
    }
    @GetMapping("/ventasucursal")
    public List<Reporte> reporteVentaSucursal() {
        return reporteService.reporteVentaSucursal();
    }
    @GetMapping("/stockcritico")
    public List<Reporte> reporteStockCritico() {
        return reporteService.reporteStockCritico();
    }
    @PostMapping
    public ResponseEntity<Reporte> generarReporte(@Valid @RequestBody Reporte reporte) {
        return ResponseEntity.ok(reporteService.generarReporte(reporte));
    }
    @PutMapping("modificar/{idReporte}")
    public ResponseEntity<Reporte> actualizarReporte(@PathVariable Long idReporte,@Valid @RequestBody Reporte reporte) {
        Reporte actualizado = reporteService.actualizarReporte(idReporte, reporte);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }
    @DeleteMapping("eliminar/{idReporte}")
    public ResponseEntity<String> eliminarReporte(@PathVariable Long idReporte) {
        reporteService.eliminarReporte(idReporte);
        return ResponseEntity.ok("Reporte eliminado correctamente");
    }
}