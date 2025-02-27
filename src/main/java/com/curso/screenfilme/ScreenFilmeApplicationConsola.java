package com.curso.screenfilme;

import com.curso.screenfilme.principal.Principal;
import com.curso.screenfilme.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenFilmeApplicationConsola implements CommandLineRunner {
	@Autowired
	private SerieRepository repositorio;
	public static void main(String[] args) {
		SpringApplication.run(ScreenFilmeApplicationConsola.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio);
		principal.mostrarMenu();
	}
}
