package com.bookpoint.review_service.service;

import com.bookpoint.review_service.model.Resena;
import com.bookpoint.review_service.repository.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    public Resena agregar(Resena resena) {
        if (resena.getCalificacion() < 1 || resena.getCalificacion() > 5) {
            throw new RuntimeException("La calificacion debe ser entre 1 y 5");
        }
        resena.setFecha(LocalDate.now());
        return resenaRepository.save(resena);
    }

    public List<Resena> listarTodas() {
        return resenaRepository.findAll();
    }

    public Optional<Resena> buscarPorId(Long id) {
        return resenaRepository.findById(id);
    }

    public List<Resena> buscarPorProducto(Long productoId) {
        return resenaRepository.findByProductoId(productoId);
    }

    public List<Resena> buscarPorUsuario(Long usuarioId) {
        return resenaRepository.findByUsuarioId(usuarioId);
    }

    public void eliminar(Long id) {
        if (!resenaRepository.existsById(id)) {
            throw new RuntimeException("Resena no encontrada");
        }
        resenaRepository.deleteById(id);
    }
}