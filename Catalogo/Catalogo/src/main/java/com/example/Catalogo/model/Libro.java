package com.example.Catalogo.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@PrimaryKeyJoinColumn(name = "idProducto")
@Table(name = "libros")

public class Libro extends Producto{
    @Column(nullable = false, unique = true)
    @NotBlank(message = "El ISBN es obligatorio")
    @Size(min = 10, max = 13, message = "El ISBN debe tener entre 10 y 13 caracteres")
    private String isbn;

    @Column(nullable = false)
    @NotBlank(message = "El autor es obligatorio")
    private String autor;
    
    @Column(nullable = false)
    @NotBlank(message = "La editorial es obligatoria")
    private String editorial;

    @Column(nullable = false)
    @NotNull(message = "La fecha de publicacion es obligatoria")
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion;

}
