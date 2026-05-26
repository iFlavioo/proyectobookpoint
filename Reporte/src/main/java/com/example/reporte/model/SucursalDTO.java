
package com.example.reporte.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SucursalDTO {

    private Long id;
    private String nombre;
    private String ciudad;
    private String direccion;
    private String horario;
}
