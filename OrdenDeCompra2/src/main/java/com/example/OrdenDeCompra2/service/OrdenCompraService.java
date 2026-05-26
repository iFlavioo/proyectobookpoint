package com.example.OrdenDeCompra2.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.OrdenDeCompra2.model.OrdenCompra;
import com.example.OrdenDeCompra2.model.ProveedorDTO;
import com.example.OrdenDeCompra2.repository.OrdenCompraRepository;

@Service
public class OrdenCompraService {
    @Autowired
    private OrdenCompraRepository ordenRepository;


    @Autowired
    private RestTemplate restTemplate;

    public void crearOrden(OrdenCompra orden) {

        String urlProveedor = "http://localhost:8097/proveedores/" + orden.getProveedorId();
        
        try {
                        ProveedorDTO proveedor = restTemplate.getForObject(urlProveedor, ProveedorDTO.class);
            
            if (proveedor == null) {
                throw new RuntimeException("El proveedor con ID " + orden.getProveedorId() + " no existe.");
            }
            

            orden.setIdOrden(null);
            
            
            ordenRepository.save(orden);
            
        } catch (Exception e) {
            throw new RuntimeException("No se pudo crear la orden. Detalles: " + e.getMessage());
        }
    }
    
    public void cancelarOrden(Long idOrden) {
        Optional<OrdenCompra> ordenOpt = ordenRepository.findById(idOrden);
        
        if (ordenOpt.isPresent()) {
            OrdenCompra orden = ordenOpt.get();
            orden.setEstado("CANCELADA");
            ordenRepository.save(orden);
        } else {
            throw new RuntimeException("La orden con ID " + idOrden + " no existe.");
        }
    }

    
    public void actualizarEstado(Long idOrden, String nuevoEstado) {
        Optional<OrdenCompra> ordenOpt = ordenRepository.findById(idOrden);
        
        if (ordenOpt.isPresent()) {
            OrdenCompra orden = ordenOpt.get();
            orden.setEstado(nuevoEstado);
            ordenRepository.save(orden);
        } else {
            throw new RuntimeException("La orden con ID " + idOrden + " no existe.");
        }
    }

    // + getOrden() : OrdenCompra
    public OrdenCompra getOrden(Long idOrden) {
        Optional<OrdenCompra> ordenOpt = ordenRepository.findById(idOrden);
        
        if (ordenOpt.isPresent()) {
            return ordenOpt.get();
        } else {
        
            throw new RuntimeException("Orden no encontrada"); 
        }
    }
}
