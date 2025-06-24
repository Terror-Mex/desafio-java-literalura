package com.adrian.desafio_java_literalura.service;

import com.adrian.desafio_java_literalura.model.Book;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class ConversorDeDatos {

    public Book obtenerLibro(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);
            JsonNode primerResultado = root.path("results").get(0);

            if (primerResultado != null && !primerResultado.isEmpty()) {
                Book libro = mapper.treeToValue(primerResultado, Book.class);
                return libro;
            }

        } catch (Exception e) {
            System.out.println("Error al convertir JSON: " + e.getMessage());
        }
        return null;
    }
}
