package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query( "SELECT m FROM Movie m JOIN FETCH m.genre WHERE m.id = :id")
    Movie findMovieWithDetails(Long id);

    @Query("SELECT m from Movie m WHERE (:genreId IS NULL OR m.genre.id IN :genreId)")
    Page<Movie> findAllPaged(List<Long> genreId, Pageable pageable);
}
