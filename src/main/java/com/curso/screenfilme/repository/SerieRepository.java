package com.curso.screenfilme.repository;

import com.curso.screenfilme.model.Categoria;
import com.curso.screenfilme.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SerieRepository extends JpaRepository<Serie, Long>{
    Optional<Serie> findByTituloContainsIgnoreCase(String tituloSerie);
    List<Serie> findTob5ByOrderByEvaluacionDesc();
    List<Serie> findByGenero(Categoria categoria);
    //List <Serie> findByTotalTemporadasLessThanEqualAndEvaluacionGreaterThanEqual();
    @Query("SELECT s FROM Serie s WHERE s.totalTemporadas >= :totalTemporadas AND s.evaluacion >= :evaluacion")
    List <Serie> seriesPorTemporadaYEvaluacion(int totalTemporadas, Double evaluacion);

}
