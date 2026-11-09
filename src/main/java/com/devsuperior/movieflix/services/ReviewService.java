package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    public ReviewDTO insert(ReviewDTO dto) {

        Review entity = new Review();
        entity.setText(dto.getText());

        Movie movie = movieRepository.getReferenceById(dto.getMovieId());
        entity.setMovie(movie);

        UserDTO userDto = userService.getProfile();
        User user = userRepository.getReferenceById(userDto.getId());
        entity.setUser(user);

        entity = repository.save(entity);

        return new ReviewDTO(entity);
    }
}
