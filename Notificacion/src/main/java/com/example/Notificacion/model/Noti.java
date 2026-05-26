package com.example.Notificacion.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table ( name = "notificacion") 

public class Noti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotif;
    private Long destinatarioId; 
    private String tipo;         
    private String mensaje; 
    private String canal;        
    private boolean leido;
}
