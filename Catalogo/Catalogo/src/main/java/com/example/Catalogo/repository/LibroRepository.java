package com.example.Catalogo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Catalogo.model.Libro;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>{

    Libro findByIsbn(String isbn);

    List<Libro> findByAutorContaining(String autor);

    List<Libro> findByEditorial(String editorial);
}

