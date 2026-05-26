package com.example.reporte.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.reporte.model.Reporte;
import com.example.reporte.model.SucursalDTO;
import com.example.reporte.repository.ReporteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Reporte> listarTodosReportes() {
        return reporteRepository.findAll();
    }

    public Reporte buscarPorIdReporte(Long idReporte) {
        return reporteRepository.findById(idReporte).orElse(null);
    }

    public List<Reporte> buscarPorDestinatario(Long destinatarioId) {
        return reporteRepository.findByDestinatarioId(destinatarioId);
    }

    public List<Reporte> buscarPorSucursal(Long sucursalId) {
        return reporteRepository.findBySucursalId(sucursalId);
    }

    public List<Reporte> reporteVentaCategoria() {
        return reporteRepository.findByTipo("VENTA_CATEGORIA");
    }

    public List<Reporte> reporteVentaSucursal() {
        return reporteRepository.findByTipo("VENTA_SUCURSAL");
    }

    public List<Reporte> reporteStockCritico() {
        return reporteRepository.findByTipo("STOCK_CRITICO");
    }

    public Reporte generarReporte(Reporte reporte) {
        try {
            SucursalDTO sucursal = restTemplate.getForObject(
                "http:localhost:8091/api/sucursales/buscarid" + reporte.getSucursalId(),
                SucursalDTO.class
            );
            if (sucursal == null) {
                return null;
            }
        } catch (Exception e) {
        
        return null;
    }

        return reporteRepository.save(reporte);
    }

    public Reporte actualizarReporte(Long idReporte, Reporte reporte) {
        Reporte existente = reporteRepository.findById(idReporte).orElse(null);
        if (existente != null) {
            existente.setDestinatarioId(reporte.getDestinatarioId());
            existente.setTipo(reporte.getTipo());
            existente.setSucursalId(reporte.getSucursalId());
            existente.setFechaInicio(reporte.getFechaInicio());
            existente.setFechaFin(reporte.getFechaFin());
            return reporteRepository.save(existente);
        }
        return null;
    }

    public void eliminarReporte(Long idReporte) {
        reporteRepository.deleteById(idReporte);
    }
}