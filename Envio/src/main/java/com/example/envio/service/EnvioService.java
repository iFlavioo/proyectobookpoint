package com.example.envio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.envio.model.Envio;
import com.example.envio.model.VentaDTO;
import com.example.envio.repository.EnvioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EnvioService {

    @Autowired
    private EnvioRepository envioRepository;
    @Autowired
    private RestTemplate restTemplate;

    public List<Envio> listarTodosEnvios() {
        return envioRepository.findAll();
    }
    public Envio buscarPorIdEnvio(Long idEnvio) {
        return envioRepository.findById(idEnvio).orElse(null);
    }
    public Envio buscarPorVentaId(Long ventaId) {
        return envioRepository.findByVentaId(ventaId);
    }
    public List<Envio> buscarPorEstado(String estado) {
        return envioRepository.findByEstado(estado);
    }   
    public Envio crearEnvio(Envio envio) {
        // verifica que la venta existe en el microservicio Venta
    try {
        VentaDTO venta = restTemplate.getForObject(
            "http://localhost:8095/api/ventas/buscarid" + envio.getVentaId(),
            VentaDTO.class
        );
        if (venta == null) {
            return null;
        }
    } catch (Exception e) {
        // si el microservicio Venta no responde no crea el envio
        return null;
    }

    envio.setEstado("PENDIENTE");
    return envioRepository.save(envio);
}
    public Envio actualizarEstado(Long idEnvio, String estado) {
        Envio existente = envioRepository.findById(idEnvio).orElse(null);
        if (existente != null) {
            existente.setEstado(estado);
            return envioRepository.save(existente);
        }
        return null;
    }
    public Envio seguirEnvio(Long idEnvio) {
        return envioRepository.findById(idEnvio).orElse(null);
    }
    public void cancelarEnvio(Long idEnvio) {
        Envio existente = envioRepository.findById(idEnvio).orElse(null);
        if (existente != null) {
            existente.setEstado("CANCELADO");
            envioRepository.save(existente);
        }
    }
    public void eliminarEnvio(Long idEnvio) {
        envioRepository.deleteById(idEnvio);
    }
    
}