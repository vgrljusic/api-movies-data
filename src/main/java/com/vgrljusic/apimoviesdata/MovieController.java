package com.vgrljusic.apimoviesdata;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vgrljusic.apimoviesdata.Models.Movie;
import com.vgrljusic.apimoviesdata.Models.MovieList;
import com.vgrljusic.apimoviesdata.Models.MovieNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private MovieService movieService;

    @RequestMapping(method = RequestMethod.GET, value="/movies")
    @ApiOperation(value= "Get all movies",
            notes = "Returns all movies in local database")
    public List<Movie> getAllMovie() {
        return movieService.getAllMovies();
    }

    @ApiOperation(value= "Get all movies online",
            notes = "Searches for all similarly named movies on http://api.tvmaze.com and find out more about their plot")
    @RequestMapping(method = RequestMethod.GET, value="/movies/allOnline/{id}")
    public List<Movie> getAllMovieOnline( @ApiParam(value = "Provide name of the movie", required = true) @PathVariable String id) {

        final String uri = "http://api.tvmaze.com/search/shows?q={q}";
        RestTemplate restTemplate = new RestTemplate();

        final Map<String, String> variables = new HashMap<>();
        variables.put("q", id);

        ResponseEntity<Object[]> response = restTemplate.getForEntity(uri, Object[].class, variables);
        Object[] objects = response.getBody();

        ObjectMapper mapper = new ObjectMapper();

        return Arrays.stream(objects)
                .map(object -> mapper.convertValue(object, MovieList.class))
                .map(MovieList::getShow)
                .collect(Collectors.toList());

    }

    @RequestMapping(method = RequestMethod.GET, value="/movies/{id}")
    @ApiOperation(value= "Get movie",
            notes = "Returns movie if found in local database")
    public Movie getMovie(@PathVariable String id) {

        Movie movie = movieService.getMovie(id);

        if(movie != null){
            return movie;
        }

        throw new MovieNotFoundException(id);

    }

    @RequestMapping(method = RequestMethod.GET, value="/movies/singleOnline/{id}")
    @ApiOperation(value= "Get single movies online and save locally afterwards",
            notes = "Searches for single movie on http://api.tvmaze.com and saves in local database")
    public Movie getSingleMovieOnline(@ApiParam(value = "Provide name of the movie", required = true) @PathVariable String id) {

        final String uri = "http://api.tvmaze.com/singlesearch/shows?q={q}";
        RestTemplate restTemplate = new RestTemplate();

        final Map<String, String> variables = new HashMap<>();
        variables.put("q", id);

        Movie onlineMovie = restTemplate.getForObject(uri, Movie.class, variables);
        movieService.addMovie(onlineMovie);

        return onlineMovie;
    }




    @RequestMapping(method = RequestMethod.POST, value="/movies")
    @ApiOperation(value= "Add movie",
            notes = "Add movie in local database")
    public void addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
    }
}
