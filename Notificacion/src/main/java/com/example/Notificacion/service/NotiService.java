package com.example.Notificacion.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Notificacion.model.Noti;
import com.example.Notificacion.repository.NotiRepository;
@Service
public class NotiService {
    @Autowired
    private NotiRepository notiRepository;


    public void enviarEmail(Long destinatarioId, String tipo, String mensaje) {
        Noti nuevaNoti = new Noti();
        nuevaNoti.setDestinatarioId(destinatarioId);
        nuevaNoti.setTipo(tipo);
        nuevaNoti.setMensaje(mensaje);
        nuevaNoti.setCanal("EMAIL");
        nuevaNoti.setLeido(false);
        
        System.out.println("Enviando EMAIL a Usuario " + destinatarioId + ": " + mensaje);
        
        notiRepository.save(nuevaNoti);
    }

    
    public void notificarStockBajo(Long destinatarioId, Long productoId) {
        String mensaje = "¡Atención! El producto con ID " + productoId + " tiene un nivel de stock crítico.";
        // Reutilizamos la función de enviar correo
        enviarEmail(destinatarioId, "ALERTA_STOCK", mensaje);
    }


    public void notificarEstadoEnvio(Long destinatarioId, Long idVenta, String estadoEnvio) {
        String mensaje = "Actualización de tu pedido #" + idVenta + ". Estado actual: " + estadoEnvio;
        enviarEmail(destinatarioId, "ESTADO_ENVIO", mensaje);
    }

    // + marcarLeido() : boolean
    public boolean marcarLeido(Long idNotif) {
        Optional<Noti> notiOpt = notiRepository.findById(idNotif);
        
        if (notiOpt.isPresent()) {
            Noti notificacion = notiOpt.get();
            notificacion.setLeido(true);
            notiRepository.save(notificacion);
            return true;
        }
        
        return false;
    }
}
