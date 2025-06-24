package com.adrian.desafio_java_literalura.principal;

import com.adrian.desafio_java_literalura.model.AuthorEntity;
import com.adrian.desafio_java_literalura.model.Book;
import com.adrian.desafio_java_literalura.model.BookEntity;
import com.adrian.desafio_java_literalura.service.ConsumoAPI;
import com.adrian.desafio_java_literalura.service.ConversorDeDatos;
import com.adrian.desafio_java_literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    private final ConsumoAPI consumoAPI;
    private final ConversorDeDatos conversor;
    private final LibroService libroService;

    @Autowired
    public Principal(ConsumoAPI consumoAPI, ConversorDeDatos conversor, LibroService libroService) {
        this.consumoAPI = consumoAPI;
        this.conversor = conversor;
        this.libroService = libroService;
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;
        while (opcion != 0) {

            System.out.println("""
                    *******************************************
                    Hola, elije una opción del menú:
                    *******************************************
                    
                    1-Buscar libro por título.
                    2-Listar libros registrados.
                    3-Listar autores registrados.
                    4-Listar autores vivos en un determinado año.
                    5-Listar libros por idioma.
                    0- Salir.
                    
                    ********************************************
                    """);
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: {
                    System.out.println("Ingrese título del libro");
                    String titulo = scanner.nextLine();
                    String json = consumoAPI.consumirGutendex(titulo);
                    Book libro = conversor.obtenerLibro(json);

                    if (libro != null) {
                        libroService.guardarLibro(libro);

                        System.out.println("Título: " + libro.getTitulo());

                        for (Book.Author autor : libro.getAutores()) {
                            System.out.println("Autor: " + autor.getName());
                            String nacimiento = (autor.getAnioNacimiento() != null)
                                    ? autor.getAnioNacimiento().toString()
                                    : "No disponible";

                            String muerte = (autor.getAnioMuerte() != null)
                                    ? autor.getAnioMuerte().toString()
                                    : "No disponible";
                            System.out.println("Año de nacimiento: " + autor.getAnioNacimiento());
                            System.out.println("Año de muerte: " + autor.getAnioMuerte());
                            System.out.println("---------------------------");
                        }
                        System.out.println("Idioma: " + libro.getIdioma());
                        System.out.println("Descargas: " + libro.getNumeroDescargas());
                    } else {
                        System.out.println("No se encontró el libro.");
                    }
                    break;
                }
                case 2: {
                    libroService.listarLibrosRegistrados();
                    break;
                }
                case 3: {
                    libroService.listarAutoresRegistrados();
                    break;
                }
                case 4: {
                    System.out.println("Ingrese un año...");
                    int anio = scanner.nextInt();
                    List<AuthorEntity> autores = libroService.listarAutoresVivosEnAnio(anio);
                    if (autores.isEmpty()) {
                        System.out.println("No se encontraron autores vivos ese año");
                    } else {
                        System.out.println("Autores vivos en el año: " + anio + ":");
                        autores.forEach(a -> System.out.println(a.getNombre()));
                    }
                    break;
                }
                case 5: {
                    System.out.println("Ingrese el idioma (ejemplo: en,es,fr):");
                    String idioma = scanner.nextLine();
                    List<BookEntity> librosPorIdioma = libroService.ListarLibrosPorIdioma(idioma);

                    if (librosPorIdioma.isEmpty()) {
                        System.out.println("No se encontraron libros en ese idioma.");
                    } else {
                        System.out.println("Libros en idioma " + idioma + ":");
                        librosPorIdioma.forEach(libro -> System.out.println("- " + libro.getTitulo()));
                    }
                    break;

                }
            }
        }
    }
}


