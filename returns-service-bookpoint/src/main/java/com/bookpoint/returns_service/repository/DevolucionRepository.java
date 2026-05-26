package com.bookpoint.returns_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookpoint.returns_service.model.Devolucion;

public interface DevolucionRepository extends JpaRepository<Devolucion, Long>{
    List<Devolucion> findByVentaId(Long ventaId); // Para buscar devoluciones asociadas a una compra
}
