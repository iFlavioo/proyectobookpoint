package com.example.envio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.envio.model.Envio;

@Repository
public interface EnvioRepository extends JpaRepository<Envio, Long> {
    // buscar envio por venta
    Envio findByVentaId(Long ventaId);
    // buscar por estado: PENDIENTE, EN_CAMINO, etc
    List<Envio> findByEstado(String estado);
}