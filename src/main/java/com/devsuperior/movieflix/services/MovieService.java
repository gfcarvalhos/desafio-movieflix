package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Transactional(readOnly = true)
    public MovieDetailsDTO findById(Long id) {
        boolean exists = repository.existsById(id);
        if(!exists) throw new ResourceNotFoundException("Filme não encontrado");

        Movie result = repository.findMovieWithDetails(id);
        return new MovieDetailsDTO(result);
    }

    @Transactional(readOnly = true)
    public Page<MovieCardDTO> findAllPaged(Pageable pageable) {
        Page<Movie> result = repository.findAll(pageable);

        return result.map(MovieCardDTO::new);
    }
}
