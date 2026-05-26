package com.example.Venta.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Venta.model.Venta;
import com.example.Venta.model.UsuarioDTO;
import com.example.Venta.repository.VentaRepository;

@Service
public class VentaService {

    @Autowired 
    private VentaRepository ventaRepository;

    
    @Autowired 
    private RestTemplate restTemplate;

    public void registrarVenta(Venta venta) {
    
        String urlUsuarios = "http://localhost:8081/api/usuarios/" + venta.getUsuarioId();
        
        try {
    
            UsuarioDTO usuario = restTemplate.getForObject(urlUsuarios, UsuarioDTO.class);
            
            if (usuario == null) {
                throw new RuntimeException("El usuario con ID " + venta.getUsuarioId() + " no existe en BookPoint.");
            }
            
            
            if (!usuario.isActivo()) {
                throw new RuntimeException("El usuario " + usuario.getNombre() + " " + usuario.getApellido() + " está INACTIVO.");
            }
            
        } catch (Exception e) {
        
            throw new RuntimeException("Validación rechazada: " + e.getMessage());
        }

        
        venta.setFechaVenta(new Date());
        venta.setEstado("PENDIENTE");  
        
        double totalCalculado = calcularTotal(venta.getTotal(), 0.19, 0.0); 
        venta.setTotal(totalCalculado);
        
        ventaRepository.save(venta);
    }

    public double calcularTotal(double subtotal, double impuestoPorcentaje, double descuento) {
        double impuestos = subtotal * impuestoPorcentaje;
        double total = (subtotal + impuestos) - descuento;
        return Math.max(total, 0.0); 
    }

    public String generarBoleta(Long idVenta) {
        Optional<Venta> ventaOpt = ventaRepository.findById(idVenta);
        
        if (ventaOpt.isPresent()) {
            Venta v = ventaOpt.get();
            
        
            String nombreCliente = "ID Usuario: " + v.getUsuarioId();
            try {
                String urlUsuarios = "http://localhost:8081/api/usuarios/" + v.getUsuarioId();
                UsuarioDTO usuario = restTemplate.getForObject(urlUsuarios, UsuarioDTO.class);
                if (usuario != null) {
                    nombreCliente = usuario.getNombre() + " " + usuario.getApellido();
                }
            } catch (Exception e) {
            
            }

            return String.format(
                "==================================\n" +
                "         BOLETA ELECTRONICA       \n" +
                "==================================\n" +
                "ID Venta    : %d\n" +
                "Cliente     : %s\n" + 
                "Fecha       : %s\n" +
                "Método Pago : %s\n" +
                "Estado      : %s\n" +
                "----------------------------------\n" +
                "TOTAL       : $%.2f\n" +
                "==================================",
                v.getIdVenta(), nombreCliente, v.getFechaVenta().toString(), 
                v.getMetodoPago(), v.getEstado(), v.getTotal()
            );
        }
        return "Error: No se pudo generar la boleta. Venta no encontrada.";
    }

    public void cancelarVenta(Long idVenta) {
        Optional<Venta> ventaOpt = ventaRepository.findById(idVenta);
        
        if (ventaOpt.isPresent()) {
            Venta venta = ventaOpt.get();
            venta.setEstado("CANCELADA");
            ventaRepository.save(venta);
        } else {
            throw new RuntimeException("La venta con ID " + idVenta + " no existe.");
        }
    }
    public Optional<Venta> buscarPorId(Long id) {
    return ventaRepository.findById(id);
}

}