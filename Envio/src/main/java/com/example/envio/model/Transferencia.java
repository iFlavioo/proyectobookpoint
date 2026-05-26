package com.example.envio.model;

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
@Table(name = "transferencias")
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransferencia;
    @Column(nullable = false)
    @NotNull(message = "La sucursal de origen es obligatoria")
    @Positive(message = "El id de sucursal origen debe ser mayor a 0")
    private Long sucursalOrigen;
    @Column(nullable = false)
    @NotNull(message = "La sucursal de destino es obligatoria")
    @Positive(message = "El id de sucursal destino debe ser mayor a 0")
    private Long sucursalDestino;
    @Column(nullable = false)
    @NotNull(message = "El id del producto es obligatorio")
    @Positive(message = "El id del producto debe ser mayor a 0")
    private Long productoId;
    @Column(nullable = false)
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private int cantidad;
    // estado: PENDIENTE, APROBADA, RECHAZADA
    @Column(nullable = false)
    @NotBlank(message = "El estado es obligatorio")
    private String estado;
    @Column(nullable = false)
    @NotNull(message = "La fecha es obligatoria")
    @Temporal(TemporalType.DATE)
    private Date fecha;
}