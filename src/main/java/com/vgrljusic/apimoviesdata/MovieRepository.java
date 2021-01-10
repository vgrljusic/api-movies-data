package com.vgrljusic.apimoviesdata;

import com.vgrljusic.apimoviesdata.Models.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, String> {
}
