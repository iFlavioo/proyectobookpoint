package com.example.Catalogo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table (name = "productos")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombreP;

    @Column(nullable = false)
    @Positive(message = "El precio debe ser mayor que 0")
    private double precioP;

    @Column(nullable = false)
    @NotBlank(message = "El tipo debe ser oblugatorio")
    private String tipo;

    @Column(length = 500)
    @NotBlank(message = "La descripcion debe ser ")
    private String descripcionP;

    private boolean disponible;
}
