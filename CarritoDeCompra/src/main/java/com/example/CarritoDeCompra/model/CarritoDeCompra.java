package com.example.CarritoDeCompra.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CarritoDeCompra {
    private Long carritoId;
    
    private Long usuarioId;
    private String estado;
    private double total;
    private Date fechaCreacion;
    private List<Long> productosIds = new ArrayList<>();
}
