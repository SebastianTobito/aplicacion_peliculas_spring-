package com.curso.screenfilme.principal;

import com.curso.screenfilme.model.DatosSerie;
import com.curso.screenfilme.model.DatosTemporada;
import com.curso.screenfilme.service.ConsumoApi;
import com.curso.screenfilme.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=e212bfb";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos conversor = new ConvierteDatos();

    public void mostrarMenu(){
        System.out.println("Escribe el nombre de la serie que quieres buscar");
        //Buscamos los datos generales de las series
        var nombreSerie = scanner.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);

        //Buscamos los datos de las temporadas
        List<DatosTemporada> temporadas = new ArrayList<>();
        for (int i = 1; i <= datos.totalTemporadas() ; i++) {
            json = consumoApi.obtenerDatos(URL_BASE+ nombreSerie.replace(" ", "+") + "&Season=" + i +API_KEY);
            var datosTemporadas = conversor.obtenerDatos(json, DatosTemporada.class);
            temporadas.add(datosTemporadas);
        }
        temporadas.forEach(System.out::println);
    }
}
