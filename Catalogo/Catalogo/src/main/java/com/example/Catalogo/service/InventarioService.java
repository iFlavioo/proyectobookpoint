package com.example.Catalogo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Catalogo.model.Inventario;
import com.example.Catalogo.repository.InventarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class InventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;

    public List <Inventario> listarTodoInventario() {
        return inventarioRepository.findAll();
    }

    public Inventario buscarPorIdInventario(Long idInventario) {
        return inventarioRepository.findById(idInventario).orElse(null);
    }

    public Inventario buscarPorProductoId(Long productoId) {
        return inventarioRepository.findByProductoId(productoId);
    }

    public List<Inventario> buscarPorSucursal(String sucursal) {
        return inventarioRepository.findBySucursal(sucursal);
    }
    public List<Inventario> listarStockBajo() {
    return inventarioRepository.findStockBajo();
    }
    public Inventario agregarInventario(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }
    public Inventario actualizarInventario(Long idInventario, Inventario inventario) {
        Inventario existente = inventarioRepository.findById(idInventario).orElse(null);
        if (existente != null) {
            existente.setCantidad(inventario.getCantidad());
            existente.setStockMinimo(inventario.getStockMinimo());
            existente.setSucursal(inventario.getSucursal());
            existente.setProductoId(inventario.getProductoId());
            return inventarioRepository.save(existente);
        }
        return null;
    }
    // agregar stock a un inventario existente
    public Inventario agregarStock(Long idInventario, int cantidad) {
        Inventario existente = inventarioRepository.findById(idInventario).orElse(null);
        if (existente != null) {
            existente.agregarStock(cantidad);
            return inventarioRepository.save(existente);
        }
        return null;
    }
    // descontar stock (cuando se hace una venta)
    public Inventario descontarStock(Long idInventario, int cantidad) {
        Inventario existente = inventarioRepository.findById(idInventario).orElse(null);
        if (existente != null) {
            existente.descontarStock(cantidad);
            return inventarioRepository.save(existente);
        }
        return null;
    }
    public void eliminarInventario(Long idInventario) {
        inventarioRepository.deleteById(idInventario);
    }
    
}
