package com.bookpoint.returns_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookpoint.returns_service.model.Devolucion;
import com.bookpoint.returns_service.repository.DevolucionRepository;

@Service
public class DevolucionService {
    @Autowired
    private DevolucionRepository repository;

    public Devolucion registrarDevolucion(Devolucion devolucion){
        return repository.save(devolucion);
    }

    public List<Devolucion> obtenerTodas() {
        return repository.findAll();
    }

    public Optional<Devolucion> obtenerPorIde(Long id){
        return repository.findById(id);
    }

    public List<Devolucion> obtenerPorVentaId(Long ventaId){
        return repository.findByVentaId(ventaId);
    }

    public Devolucion actualizarEstado(Long id, String nuevoEstado) {
        return repository.findById(id).map(devolucion -> {
            devolucion.setEstado(nuevoEstado.toUpperCase());
            return repository.save(devolucion);
        }).orElseThrow(() -> new RuntimeException("Devolución no encontrada con ID: " + id));
    }
}
