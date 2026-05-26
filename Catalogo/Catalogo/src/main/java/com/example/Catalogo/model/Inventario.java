package com.example.Catalogo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventario")

public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInventario;

    @Column(nullable = false)
    @NotNull(message = "El id del producto es obligatorio")
    @Positive(message = "El id del producto debe ser mayor a 0")
    private Long productoId;

    @Column(nullable = false)
    @Min(value = 0, message = "La cantidad no puede ser negativa")
    private int cantidad;

    @Column(nullable = false)
    @Min(value = 0, message = "El stock minimo no puede ser negativo")
    private int stockMinimo;

    @Column(nullable = false)
    @NotBlank(message = "La sucursal es obligatoria")
    private String sucursal;

    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaActualizacion;
    
    // metodo para saber si hay stock disponible
    public boolean hayStock() {
        return this.cantidad > 0;
    }
    // metodo para saber si el stock esta bajo el minimo
    public boolean alertaStockBajo() {
        return this.cantidad <= this.stockMinimo;
    }
    // metodo para agregar stock
    public void agregarStock(int cantidad) {
        this.cantidad += cantidad;
        this.ultimaActualizacion = new Date();
    }
    // metodo para descontar stock al hacer una venta
    public void descontarStock(int cantidad) {
        if (this.cantidad >= cantidad) {
            this.cantidad -= cantidad;
            this.ultimaActualizacion = new Date();
        }
    }
}
