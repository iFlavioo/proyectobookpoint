package com.example.envio.model;

import java.util.Date;

import jakarta.persistence.Id;

public class VentaDTO {
    public class Venta {
    @Id
    private Long idVenta;
    private Long usuarioId;
    private Date fechaVenta; 
    private  double total;
    private String metodoPago; 
    private  String estado;
}
}
