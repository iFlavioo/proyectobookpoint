package com.example.Catalogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Catalogo.model.Producto;

@Repository

public interface ProductoRepository extends JpaRepository < Producto,Long > {

    List<Producto> findByTipo(String tipo);

    List<Producto> findByDisponibleTrue();

    List<Producto> findByNombrePContaining(String nombreP);
}
