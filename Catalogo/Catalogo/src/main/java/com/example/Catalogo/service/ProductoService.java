package com.example.Catalogo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Catalogo.model.Producto;
import com.example.Catalogo.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService {

@Autowired
private ProductoRepository productoRepository;

public List<Producto> listarTodoProductos(){
    return productoRepository.findAll();
} 
public Producto busacarPorIdProductos(Long idProducto){
    return productoRepository.findById(idProducto).orElse(null);
}
public List <Producto> buscarPorTipo(String tipo){
    return productoRepository.findByTipo(tipo);
}
public List <Producto> ListarProductoDisponible(){
    return productoRepository.findByDisponibleTrue();
}
public List<Producto> buscarPorNombreProductos(String nombreP){
    return productoRepository.findByNombrePContaining(nombreP);
}
public Producto agregarProducto(Producto producto){
    return productoRepository.save(producto);
}
public Producto actualizaProducto(Long idProducto,Producto producto){
        Producto existente = productoRepository.findById(idProducto).orElse(null);
        if(existente != null){
        existente.setNombreP(producto.getNombreP());
        existente.setPrecioP(producto.getPrecioP());
        existente.setTipo(producto.getTipo());
        existente.setDescripcionP(producto.getDescripcionP());
        existente.setDisponible(producto.isDisponible());
        return productoRepository.save(existente);
        }
        return null;
}

public void eliminarProducto(Long idProducto){
    productoRepository.deleteById(idProducto);
}

}
