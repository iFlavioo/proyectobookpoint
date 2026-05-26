package com.example.Catalogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Catalogo.model.Inventario;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {


    Inventario findByProductoId(Long productoId);

    List<Inventario> findBySucursal(String sucursal);

        @Query("SELECT i FROM Inventario i WHERE i.cantidad <= i.stockMinimo")
    List<Inventario> findStockBajo();
}