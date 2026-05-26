package com.example.Notificacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Notificacion.service.NotiService;
@RestController
@RequestMapping("/notificaciones")
public class NotiController {
    @Autowired
    private NotiService notiService;

    @PostMapping("/email")
    public ResponseEntity<String> enviarEmail(@RequestParam Long destinatarioId, 
                                            @RequestParam String tipo, 
                                            @RequestParam String mensaje) {
        notiService.enviarEmail(destinatarioId, tipo, mensaje);
        return ResponseEntity.ok("Email enviado y notificacion registrada.");
    }

    @PostMapping("/stock-bajo")
    public ResponseEntity<String> notificarStockBajo(@RequestParam Long adminId, 
                                                    @RequestParam Long productoId) {
        notiService.notificarStockBajo(adminId, productoId);
        return ResponseEntity.ok("Alerta de stock bajo enviada.");
    }


    @PostMapping("/estado-envio")
    public ResponseEntity<String> notificarEstadoEnvio(@RequestParam Long usuarioId, 
                                                    @RequestParam Long idVenta, 
                                                    @RequestParam String estado) {
        notiService.notificarEstadoEnvio(usuarioId, idVenta, estado);
        return ResponseEntity.ok("Notificación de envío actualizada.");
    }


    @PutMapping("/leer/{idNotif}")
    public ResponseEntity<String> marcarComoLeido(@PathVariable Long idNotif) {
        boolean exito = notiService.marcarLeido(idNotif);
        if (exito) {
            return ResponseEntity.ok("Notificación marcada como leída.");
        } else {
            return ResponseEntity.badRequest().body("Error: Notificación no encontrada.");
        }
    }
}
