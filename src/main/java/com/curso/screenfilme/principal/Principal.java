package com.curso.screenfilme.principal;

import com.curso.screenfilme.model.DatosEpisodio;
import com.curso.screenfilme.model.DatosSerie;
import com.curso.screenfilme.model.DatosTemporada;
import com.curso.screenfilme.model.Episodio;
import com.curso.screenfilme.service.ConsumoApi;
import com.curso.screenfilme.service.ConvierteDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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
        // temporadas.forEach(System.out::println);

//        //mostrando solo los titulos de los episodios de las temporadas
//        for (int i = 0; i <datos.totalTemporadas() ; i++) {
//            List <DatosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//            for (int e = 0; e < episodiosTemporada.size(); e++) {
//                System.out.println(episodiosTemporada.get(e).titulo());
//            }
//        }
        //temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.numeroEpisodio() +". "+ e.titulo())));
        //convertir todas las informaciones a una lista del tipo DatosEpisodio
        List<DatosEpisodio> datosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

//        System.out.println("top 5 episodios por temporada");
//        datosEpisodios.stream()
//                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
//                .peek(e -> System.out.println("primer filtro (N/A)"+e))
//                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
//                .peek(e -> System.out.println("segundo filtro ordenando (mayor a menor)"+e))
//                .map(e-> e.titulo().toUpperCase())
//                .peek(e -> System.out.println("tercer filtro pasando a mayusculas (m>M)"+e))
//                .limit(5)
//                .peek(e -> System.out.println("cuarto filtro limitando a 5 los resultados(5)"+e))
//                .forEach(System.out::println);
//
        //convirtiendo los datos a una lista del tipo episodio
        List<Episodio> episodios = temporadas.stream()
                .flatMap(t-> t.episodios().stream()
                        .map(d->new Episodio(t.numero(),d)))
                        .collect(Collectors.toList());

       // episodios.forEach(System.out::println);


        //busqueda de episodios a partir de x año
        //System.out.println("Indica el año a partir del cual quieres ver los episodios");
//        var fecha = scanner.nextInt();
//        scanner.nextLine();
//        LocalDate fechaBusqueda = LocalDate.of(fecha, 1, 1);
//        DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //episodios.stream()
//                .filter(e -> e.getFechaLanzamiento() != null &&  e.getFechaLanzamiento().isAfter(fechaBusqueda))
//                .forEach(e -> System.out.println("Temporada " +e.getNumeroTemporada() +
//                        " Episdoio "+e.getTitutlo()+ " Fecha de lazamiento " + e.getFechaLanzamiento().format(dateTime)
//                ));

        //Busqueda episodios por un pedazo de us título
//        System.out.println("Escribe el nombre del episodio que desea ver");
//        var pedazoTitulio = scanner.nextLine();
//        Optional<Episodio> episodioBuscado = episodios.stream()
//                .filter(e -> e.getTitutlo().toUpperCase().contains(pedazoTitulio.toUpperCase()))
//                .findFirst();
//        if (episodioBuscado.isPresent()){
//            System.out.println("Episodio encontrado ");
//            System.out.println("Estos son los datos " + episodioBuscado.get());
//        }else {
//            System.out.println("Episodio no encontrado");
//        }
        Map<Integer, Double> evalucacionesPorTemporada = episodios.stream()
                .filter(e->e.getEvaluacion()>0.0)
                .collect(Collectors.groupingBy(Episodio::getNumeroTemporada,
                        Collectors.averagingDouble(Episodio::getEvaluacion)));
        System.out.println(evalucacionesPorTemporada);

        DoubleSummaryStatistics estadisticas = episodios.stream()
                .filter(e->e.getEvaluacion()>0.0)
                .collect(Collectors.summarizingDouble(Episodio::getEvaluacion));
        System.out.println("Media de evaluaciones: " +estadisticas.getAverage());
        System.out.println("Episodio mejor evaluado: "+ estadisticas.getMax());
        System.out.println("Episodio peor evaluado: " + estadisticas.getMin());
    }
}
