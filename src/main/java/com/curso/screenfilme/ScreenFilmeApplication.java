package com.curso.screenfilme;

import com.curso.screenfilme.principal.Principal;
import com.curso.screenfilme.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenFilmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScreenFilmeApplication.class, args);
	}


}
