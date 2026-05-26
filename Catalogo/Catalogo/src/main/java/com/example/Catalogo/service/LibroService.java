package com.example.Catalogo.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Catalogo.model.Libro;
import com.example.Catalogo.repository.LibroRepository;
import jakarta.transaction.Transactional;





@Service
@Transactional

public class LibroService {
    @Autowired
    private LibroRepository libroRepository;
    public List<Libro> listarTodosLibros() {
        return libroRepository.findAll();
    }
    public Libro buscarPorIdLibro(Long idLibro) {
        return libroRepository.findById(idLibro).orElse(null);
    }
    public Libro buscarPorIsbn(String isbn) {
        return libroRepository.findByIsbn(isbn);
    }
    public List<Libro> buscarPorAutor(String autor) {
        return libroRepository.findByAutorContaining(autor);
    }
    public List<Libro> buscarPorEditorial(String editorial) {
        return libroRepository.findByEditorial(editorial);
    }

    public Libro agregarLibro(Libro libro) {
        return libroRepository.save(libro);
    }
    public Libro actualizarLibro(Long idLibro, Libro datos) {
        Libro existente = libroRepository.findById(idLibro).orElse(null);
        if (existente != null) {
            existente.setIsbn(datos.getIsbn());
            existente.setAutor(datos.getAutor());
            existente.setEditorial(datos.getEditorial());
            existente.setFechaPublicacion(datos.getFechaPublicacion());
            existente.setNombreP(datos.getNombreP());
            existente.setPrecioP(datos.getPrecioP());
            existente.setDescripcionP(datos.getDescripcionP());
            existente.setDisponible(datos.isDisponible());
            return libroRepository.save(existente);
        }
        return null;
    }

    public void eliminarLibro(Long idLibro) {
        libroRepository.deleteById(idLibro);
    }
}

