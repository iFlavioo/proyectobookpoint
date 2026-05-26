package com.example.CarritoDeCompra.model;

import lombok.Data;

@Data
public class ProductoDTO {
private long idProducto ;
private  String nombre ; 
private double precio ;  
private String tipo ;
private  String descripcion; 
private  boolean disponible;

}
