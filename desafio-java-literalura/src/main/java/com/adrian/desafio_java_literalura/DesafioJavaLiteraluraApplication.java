package com.adrian.desafio_java_literalura;

import com.adrian.desafio_java_literalura.service.ConsumoAPI;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioJavaLiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DesafioJavaLiteraluraApplication.class, args);
	}

	public void run(String... args){

		System.out.println("Iniciando aplicación por consola");
		ConsumoAPI api = new ConsumoAPI();
		api.consumirGutendex();
	}
}
