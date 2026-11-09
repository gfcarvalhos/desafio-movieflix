package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query( "SELECT m FROM Movie m JOIN FETCH m.genre WHERE m.id = :id")
    List<Movie> findMovieWithDetails(Long id);
}
