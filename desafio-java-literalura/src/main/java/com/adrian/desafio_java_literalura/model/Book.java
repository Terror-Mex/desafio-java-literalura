package com.adrian.desafio_java_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    @JsonAlias("title")
    private String titulo;
    @JsonAlias("authors")
    private List<Author> autores;
    @JsonAlias("languages")
    private  List<String> idiomas;
    @JsonAlias("download_count")
    private Long numeroDescargas;

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        // Devolver el primer autor si existe
        return (autores != null && !autores.isEmpty()) ? autores.get(0).getName(): "Autor desconocido";
    }

    public String getIdioma() {
        return (idiomas != null && !idiomas.isEmpty()) ? idiomas.get(0) : "Idioma desconocido";
    }

    public Long getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Author> getAutores() {
        return autores;
    }

    public void setAutores(List<Author> autores) {
        this.autores = autores;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public void setNumeroDescargas(Long numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    // Clase interna para los autores
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Author {
        @JsonAlias("name")
        private String nombre;
        @JsonAlias("birth_year")
        private Integer anioNacimiento;
        @JsonAlias("death_year")
        private Integer anioMuerte;

        public Integer getAnioNacimiento() {
            return anioNacimiento;
        }

        public void setAnioNacimiento(Integer anioNacimiento) {
            this.anioNacimiento = anioNacimiento;
        }

        public Integer getAnioMuerte() {
            return anioMuerte;
        }

        public void setAnioMuerte(Integer anioMuerte) {
            this.anioMuerte = anioMuerte;
        }

        public String getName() {
            return nombre;
        }
        public void setName(String nombre) {
            this.nombre = nombre;
        }
    }
}


