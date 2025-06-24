package com.adrian.desafio_java_literalura;

import com.adrian.desafio_java_literalura.principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioJavaLiteraluraApplication implements CommandLineRunner {

	private final Principal principal;

	@Autowired
	public DesafioJavaLiteraluraApplication(Principal principal) {
		this.principal = principal;
	}
	public static void main(String[] args) {
		SpringApplication.run(DesafioJavaLiteraluraApplication.class, args);
	}

    @Override
	public void run(String... args){

		System.out.println("Iniciando aplicación por consola");
		principal.menu();
	}
}
