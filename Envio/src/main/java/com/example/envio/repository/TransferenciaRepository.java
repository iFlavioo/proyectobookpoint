package com.example.envio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.envio.model.Transferencia;
    @Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    List<Transferencia> findBySucursalOrigen(Long sucursalOrigen);
    List<Transferencia> findBySucursalDestino(Long sucursalDestino);
    List<Transferencia> findByEstado(String estado);
}

