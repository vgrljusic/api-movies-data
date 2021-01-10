package com.vgrljusic.apimoviesdata.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vgrljusic.apimoviesdata.Models.Movie;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieList {

    private String score;
    private Movie show;

    public MovieList() {


    }
    public MovieList(String score, Movie show) {
        this.score = score;
        this.show = show;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Movie getShow() {
        return show;
    }

    public void setShow(Movie show) {
        this.show = show;
    }
}



