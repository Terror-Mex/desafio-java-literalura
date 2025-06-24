package com.adrian.desafio_java_literalura.repository;

import com.adrian.desafio_java_literalura.model.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    Optional<AuthorEntity> findByNombre(String nombre);

    @Query("SELECT a FROM AuthorEntity a WHERE a.fechaNacimiento <= :anio AND (a.fechaMuerte IS NULL OR a.fechaMuerte >= :anio)")
    List<AuthorEntity> buscarAutoresVivosEnAnio(int anio);

    @Query("SELECT a.nombre FROM AuthorEntity a ORDER BY a.nombre")
    List<String> findAllNombresOrderByNombre();
}
