package com.example.reporte.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
@Table(name = "reportes")

public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReporte;

    @Column(nullable = false)
    @NotNull(message = "El id del destinatario es obligatorio")
    @Positive(message = "El id del destinatario debe ser mayor a 0")
    private Long destinatarioId;

    @Column(nullable = false)
    @NotBlank(message = "El tipo de reporte es obligatorio")
    private String tipo;

    @Column(nullable = false)
    @NotNull(message = "El id de la sucursal es obligatorio")
    @Positive(message = "El id de la sucursal debe ser mayor a 0")
    private Long sucursalId;

    @Column(nullable = false)

    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
}