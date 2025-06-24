package com.adrian.desafio_java_literalura.service;

import com.adrian.desafio_java_literalura.model.AuthorEntity;
import com.adrian.desafio_java_literalura.model.Book;
import com.adrian.desafio_java_literalura.model.BookEntity;
import com.adrian.desafio_java_literalura.repository.AuthorRepository;
import com.adrian.desafio_java_literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private BookRepository repository;
    @Autowired
    private AuthorRepository authorRepository;

    public BookEntity convertir(Book book) {
        List<AuthorEntity> autores = book.getAutores()
                .stream()
                .map(this::obtenerOInsertarAutor)
                .toList();

        return new BookEntity(
                book.getTitulo(),
                book.getIdioma(),
                book.getNumeroDescargas(),
                autores);
    }

    public BookEntity guardarLibro(Book libro) {
        BookEntity entidad = convertir(libro);

        return guardarSiNoExiste(entidad);
    }

    public BookEntity guardarSiNoExiste(BookEntity nuevoLibro) {
        Optional<BookEntity> existente = repository.findByTitulo(nuevoLibro.getTitulo());

        if (existente.isPresent()) {
            return existente.get();  // Ya existe, lo retornamos
        }
        return repository.save(nuevoLibro);  // No existe, lo guardamos
    }

    public void listarLibrosRegistrados() {
        List<BookEntity> libros = repository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados");
        } else {
            System.out.println("Libros registrados: ");
            libros.forEach(l -> {
                System.out.println("- " + l.getTitulo());
            });
        }
    }

    public List<String> listarAutoresRegistrados() {
        List<String> autoresUnicos = authorRepository.findAllNombresOrderByNombre();
        System.out.println("Autores registrados: ");
        autoresUnicos.forEach(a -> System.out.println("- " + a));
        return autoresUnicos;
    }

    private AuthorEntity obtenerOInsertarAutor(Book.Author autor) {
        return authorRepository.findByNombre(autor.getName())
                .orElseGet(() -> authorRepository.save(new AuthorEntity(
                        autor.getName(),
                        autor.getAnioNacimiento(),
                        autor.getAnioMuerte()
                )));
    }

    public List<AuthorEntity> listarAutoresVivosEnAnio(int anio) {
        return authorRepository.buscarAutoresVivosEnAnio(anio);
    }

    public List<BookEntity> ListarLibrosPorIdioma(String idioma) {
        return repository.buscarPorIdioma(idioma);
    }
}
