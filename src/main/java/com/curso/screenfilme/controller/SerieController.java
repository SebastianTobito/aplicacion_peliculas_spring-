package com.curso.screenfilme.controller;

import com.curso.screenfilme.dto.EpisodioDTO;
import com.curso.screenfilme.dto.SerieDTO;

import com.curso.screenfilme.repository.SerieRepository;
import com.curso.screenfilme.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/series")
public class SerieController {
    @Autowired
    private SerieService servicio;

    @GetMapping("/top5")
    public List<SerieDTO> obtenerTop5() {
        return servicio.obtenerTop5();
    }

    @GetMapping("/lanzamientos")
    public List<SerieDTO> obtenerLanzamientosMasRecientes() {
        return servicio.obtenerLanzamientosMasRecientes();
    }

    @GetMapping()
    public List<SerieDTO> obtenerTodasLasSeries() {
        return servicio.obtenerTodasLasSeries();
    }

    @GetMapping("/{id}")
    public SerieDTO optenerPorId(@PathVariable Long id) {
        return servicio.obtenerPorId(id);
    }

    @GetMapping("/{id}/temporadas/todas")
    public List<EpisodioDTO> obtenerTodasLasTemporadas(@PathVariable Long id) {
        return servicio.obtenerTodasLasTemporadas(id);
    }

    @GetMapping("/{id}/temporadas/{numeroTemporada}")
    public List<EpisodioDTO> optenerTemporadasPorNumero(@PathVariable Long id,
                                                        @PathVariable Long numeroTemporada){
        return servicio.obtenerTemporadasPorNumero(id, numeroTemporada);
    }
    @GetMapping("/categoria/{nombreGenero}")
    public List <SerieDTO> obtenerSeriesPorCategoria(@PathVariable String nombreGenero){
        return servicio.obtenerSeriesPorCategoria(nombreGenero);
    }
}
