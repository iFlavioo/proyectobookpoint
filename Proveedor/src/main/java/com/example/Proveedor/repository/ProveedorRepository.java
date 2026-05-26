package com.example.Proveedor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Proveedor.model.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
    
}
