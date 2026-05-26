package com.example.CarritoDeCompra.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.CarritoDeCompra.model.CarritoDeCompra;
import com.example.CarritoDeCompra.model.ProductoDTO;
import com.example.CarritoDeCompra.model.UsuarioDTO;
import com.example.CarritoDeCompra.repository.CarritoDeCompraRepository;

@Service
public class CarritoDeCompraService {
    @Autowired CarritoDeCompraRepository carritoDeCompraRepository;
    @Autowired private RestTemplate restTemplate;

public CarritoDeCompra obtenerOCrearCarrito(Long usuarioId) {
        return carritoDeCompraRepository.findByUsuarioIdAndEstado(usuarioId, "ACTIVO")
                .orElseGet(() -> {
                    CarritoDeCompra nuevo = new CarritoDeCompra();
                    nuevo.setUsuarioId(usuarioId);
                    nuevo.setEstado("ACTIVO");
                    nuevo.setFechaCreacion((java.sql.Date) new Date());
                    nuevo.setTotal(0.0);
                    return carritoDeCompraRepository.save(nuevo);
                });
    }

    public void agregarProducto(Long usuarioId, Long idProducto, double precioProducto) {
        CarritoDeCompra carrito = obtenerOCrearCarrito(usuarioId);
        
        carrito.getProductosIds().add(idProducto);
        carrito.setTotal(carrito.getTotal() + precioProducto);
        
        carritoDeCompraRepository.save(carrito);
    }

    public void eliminarProducto(Long usuarioId, Long idProducto, double precioProducto) {
        CarritoDeCompra carrito = obtenerOCrearCarrito(usuarioId);
        
        if (carrito.getProductosIds().remove(idProducto)) {
            double nuevoTotal = carrito.getTotal() - precioProducto;
            carrito.setTotal(Math.max(nuevoTotal, 0.0));
            carritoDeCompraRepository.save(carrito);
        }
    }

    public double calcularTotal(Long usuarioId) {
        CarritoDeCompra carrito = obtenerOCrearCarrito(usuarioId);
        return carrito.getTotal();
    }

    public void vaciarCarrito(Long usuarioId) {
        CarritoDeCompra carrito = obtenerOCrearCarrito(usuarioId);
        carrito.getProductosIds().clear();
        carrito.setTotal(0.0);
        carritoDeCompraRepository.save(carrito);
    }

    public void aplicarDescuento(Long usuarioId, String codigoDescuento) {
        CarritoDeCompra carrito = obtenerOCrearCarrito(usuarioId);
        
        if ("DESCUENTO10".equals(codigoDescuento)) {
            carrito.setTotal(carrito.getTotal() * 0.90);
            carritoDeCompraRepository.save(carrito);
        }
    }
public void agregarProductoAlCarrito(Long usuarioId, Long productoId, int cantidad) {
    
    String urlUsuarios = "http://localhost:8081/api/usuarios/" + usuarioId; 
    UsuarioDTO usuario = null;

    try {
        usuario = restTemplate.getForObject(urlUsuarios, UsuarioDTO.class);

        if (usuario == null) {
            throw new RuntimeException("No se puede armar el carrito: El usuario no existe.");
        }
        if (!usuario.isActivo()) {
            throw new RuntimeException("El usuario " + usuario.getNombre() + " está INACTIVO y no puede comprar.");
        }
    } catch (Exception e) {
        throw new RuntimeException("Error al conectar con el Microservicio de Usuarios: " + e.getMessage());
    }

    String urlProductos = "http://localhost:8091/api/productos/" + productoId;
    ProductoDTO producto = null; 

    try {
        producto = restTemplate.getForObject(urlProductos, ProductoDTO.class);

        if (producto == null) {
            throw new RuntimeException("No se puede agregar al carrito: El producto no existe.");
        }

        if (!producto.isDisponible()) {
            throw new RuntimeException("El producto '" + producto.getNombre() + "' no se encuentra DISPONIBLE actualmente.");
        }
        
    } catch (Exception e) {
        throw new RuntimeException("Error al conectar con el Microservicio de Productos: " + e.getMessage());
    }


    System.out.println("Validación exitosa para el usuario " + usuario.getNombre() 
        + " comprando el producto " + producto.getNombre());

}
}
