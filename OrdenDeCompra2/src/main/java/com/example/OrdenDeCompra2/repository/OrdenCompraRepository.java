package com.example.OrdenDeCompra2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.OrdenDeCompra2.model.OrdenCompra;
@Repository
public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Long>{
    
}
