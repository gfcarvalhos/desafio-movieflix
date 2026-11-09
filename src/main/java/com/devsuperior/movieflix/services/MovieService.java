package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    public List<MovieDetailsDTO> findById(Long id) {
        List<Movie> result = repository.findMovieWithDetails(id);
        if(result.isEmpty()) throw new ResourceNotFoundException("Filme não encontrado");
        return result.stream().map(MovieDetailsDTO::new).toList();
    }
}
