package com.example.reporte.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.reporte.model.Reporte;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {

    List<Reporte> findByTipo(String tipo);
    List<Reporte> findBySucursalId(Long sucursalId);
    List<Reporte> findByDestinatarioId(Long destinatarioId);
}