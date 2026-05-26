package com.example.Proveedor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Proveedor.model.Proveedor;
import com.example.Proveedor.repository.ProveedorRepository;

@Service
public class ProveedorService {
    @Autowired ProveedorRepository proveedorRepository;

    public Proveedor crear(Proveedor proveedor){
        return proveedorRepository.save(proveedor);
    }
public Proveedor obtenerPorId(Long id) {
        return proveedorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proveedor no encontrado con el ID: " + id));
    }

    public List<Proveedor> listar() {
        return proveedorRepository.findAll();
    }

    public Proveedor actualizar(Long id, Proveedor proveedor) {
        Proveedor existente=proveedorRepository.findById(id).orElse(null);
        if (existente!=null){
            existente.setNombreEmpresa(proveedor.getNombreEmpresa());
            existente.setRutEmpresa(proveedor.getRutEmpresa());
            existente.setEmail(proveedor.getEmail());
            existente.setTelefonoProveedor(proveedor.getTelefonoProveedor());
            existente.setDireccionEmpresa(proveedor.getDireccionEmpresa());
            return proveedorRepository.save(existente);
            
        }
        return null;
    }

    public void eliminar(Long id) {
        proveedorRepository.deleteById(id);
    }
    
}
    

