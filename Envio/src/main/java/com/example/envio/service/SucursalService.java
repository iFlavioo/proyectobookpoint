package com.example.envio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.envio.model.Sucursal;
import com.example.envio.repository.SucursalRepository;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    public List<Sucursal> listarTodasSucursales() {
        return sucursalRepository.findAll();
    }
    public Sucursal buscarPorIdSucursal(Long id) {
        return sucursalRepository.findById(id).orElse(null);
    }
    public List<Sucursal> buscarPorCiudad(String ciudad) {
        return sucursalRepository.findByCiudad(ciudad);
    }
    public Sucursal buscarPorNombre(String nombre) {
        return sucursalRepository.findByNombre(nombre);
    }
    public Sucursal registrarSucursal(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }
    public Sucursal actualizarSucursal(Long id, Sucursal sucursal) {
        Sucursal existente = sucursalRepository.findById(id).orElse(null);
        if (existente != null) {
            existente.setNombre(sucursal.getNombre());
            existente.setCiudad(sucursal.getCiudad());
            existente.setDireccion(sucursal.getDireccion());
            existente.setHorario(sucursal.getHorario());
            return sucursalRepository.save(existente);
        }
        return null;
    }
    public void eliminarSucursal(Long id) {
        sucursalRepository.deleteById(id);
    }
}
