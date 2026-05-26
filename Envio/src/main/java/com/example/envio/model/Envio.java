package com.example.envio.model;

import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "envios")
public class Envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEnvio;

    @Column(nullable = false)
    @NotNull(message = "El id de la venta es obligatorio")
    @Positive(message = "El id de la venta debe ser mayor a 0")
    private Long ventaId;

    @Column(nullable = false)
    @NotBlank(message = "La direccion de destino es obligatoria")
    private String direccionDestino;

    // estado: PENDIENTE, EN_CAMINO, ENTREGADO, CANCELADO
    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaEnvio;
    
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;
}