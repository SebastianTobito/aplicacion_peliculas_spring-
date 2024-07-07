package com.curso.screenfilme;

import com.curso.screenfilme.model.DatosSerie;
import com.curso.screenfilme.service.ConsumoApi;
import com.curso.screenfilme.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenFilmeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenFilmeApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		var consumoApi = new ConsumoApi();
		var json = consumoApi.obtenerDatos("http://www.omdbapi.com/?t=dragon+ball&apikey=e212bfb");
		//var json = consumoApi.obtenerDatos("https://coffee.alexflipnote.dev/random.json");
		System.out.println(json);
		ConvierteDatos conversor = new ConvierteDatos();
		var datos = conversor.obtenerDatos(json, DatosSerie.class);
		System.out.println(datos);

	}
}
