package com.example.CarritoDeCompra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CarritoDeCompra.model.CarritoDeCompra;
@Repository
public interface CarritoDeCompraRepository extends JpaRepository<CarritoDeCompra, Long>{
    Optional<CarritoDeCompra> findByUsuarioIdAndEstado(Long usuarioId, String estado);
    

    
}
