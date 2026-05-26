package com.example.envio.model;

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
@Table(name = "sucursales")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El nombre de la sucursal es obligatorio")
    private String nombre;

    @Column(nullable = false)
    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;

    @Column(nullable = false)
    @NotBlank(message = "La direccion es obligatoria")
    private String direccion;

    @Column(nullable = false)
    @NotBlank(message = "El horario es obligatorio")
    private String horario;
}
