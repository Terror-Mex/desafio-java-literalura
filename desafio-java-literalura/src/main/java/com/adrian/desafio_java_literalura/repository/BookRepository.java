package com.adrian.desafio_java_literalura.repository;

import com.adrian.desafio_java_literalura.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    Optional<BookEntity> findByTitulo(String titulo);
    @Query("SELECT b FROM BookEntity b WHERE b.idioma = :idioma ORDER BY b.titulo")
    List<BookEntity> buscarPorIdioma(@Param("idioma") String idioma);



}
