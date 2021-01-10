package com.vgrljusic.apimoviesdata;

import com.vgrljusic.apimoviesdata.Models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll()
                .forEach(movies::add);
        return movies;
    }

    public Movie getMovie(String id) {
        return movieRepository.findById(id).get();
    }

    public void addMovie(Movie movie) {
        movieRepository.save(movie);
    }
}
