package com.example.envio.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.envio.model.ProductoDTO;
import com.example.envio.model.Transferencia;
import com.example.envio.repository.TransferenciaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;
    @Autowired
    private RestTemplate restTemplate;

    public List<Transferencia> listarTodasTransferencias() {
        return transferenciaRepository.findAll();
    }

    public Transferencia buscarPorIdTransferencia(Long idTransferencia) {
        return transferenciaRepository.findById(idTransferencia).orElse(null);
    }

    public List<Transferencia> buscarPorOrigen(Long sucursalOrigen) {
        return transferenciaRepository.findBySucursalOrigen(sucursalOrigen);
    }

    public List<Transferencia> buscarPorEstado(String estado) {
        return transferenciaRepository.findByEstado(estado);
    }

    public Transferencia solicitarTransferencia(Transferencia transferencia) {
        try {
            ProductoDTO producto = restTemplate.getForObject(
                "http://localhost:8090/api/productos/" + transferencia.getProductoId(),
                ProductoDTO.class
            );
            if (producto == null) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

        // toda transferencia parte como PENDIENTE
        transferencia.setEstado("PENDIENTE");
        transferencia.setFecha(new Date());
        return transferenciaRepository.save(transferencia);
    }

    public Transferencia aprobarTransferencia(Long idTransferencia) {
        Transferencia existente = transferenciaRepository.findById(idTransferencia).orElse(null);
        if (existente != null) {
            existente.setEstado("APROBADA");
            return transferenciaRepository.save(existente);
        }
        return null;
    }

    public Transferencia rechazarTransferencia(Long idTransferencia) {
        Transferencia existente = transferenciaRepository.findById(idTransferencia).orElse(null);
        if (existente != null) {
            existente.setEstado("RECHAZADA");
            return transferenciaRepository.save(existente);
        }
        return null;
    }

    public void eliminarTransferencia(Long idTransferencia) {
        transferenciaRepository.deleteById(idTransferencia);
    }
}
