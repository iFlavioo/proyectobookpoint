package com.example.CarritoDeCompra.model;

import lombok.Data;

@Data
public class UsuarioDTO {
private Long id;
private String nombre;  
private String  apellido;  
private String  correo;  
private String  contreseña;  
private String  telefono;  
private String rol;  
private boolean  activo ;  
private boolean disponible;
}
