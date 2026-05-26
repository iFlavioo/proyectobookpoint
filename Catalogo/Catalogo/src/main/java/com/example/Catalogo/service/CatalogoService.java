package com.example.Catalogo.service;

import java.util.List;

import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Catalogo.model.Catalogo;
import com.example.Catalogo.repository.CatalogoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CatalogoService {
    @Autowired
    private CatalogoRepository catalogoRepository;

public List<Catalogo> listar(){
    return catalogoRepository.findAll();
}

public Catalogo buscarPorId(long idCatalogo){
    return catalogoRepository.findById(idCatalogo).orElse(null);
    }
public Catalogo buscarPorNombre(String nombre){
    return catalogoRepository.findByNombre(nombre);
}
public Catalogo agregarCatalogo(Catalogo catalogo){
    return catalogoRepository.save(catalogo);
}
public Catalogo actualizarCatalogo(long idCatalogo, Catalogo catalogo) {
    Catalogo existente = catalogoRepository.findById(idCatalogo).orElse(null);
    if (existente != null) {
        existente.setNombre(catalogo.getNombre());
        existente.setDescripcion(catalogo.getDescripcion());
        return catalogoRepository.save(existente);
    }
    return null;
}
public Catalogo eliminarCatalogo(long idCatalogo){
    catalogoRepository.deleteById(idCatalogo);
    return null;
}
}
