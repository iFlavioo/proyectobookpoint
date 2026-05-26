package com.example.Catalogo.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "catalogos")


public class Catalogo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCatalogo;

    @Column(nullable=false)
    @NotBlank(message = "El nombre del catalogo es obligatorio")
    private String nombre;
    

    @Column(length = 500)
    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;

}
