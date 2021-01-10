package com.vgrljusic.apimoviesdata.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

@ApiModel(description = "Details about the movie")
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    @Id
    @ApiModelProperty(notes = "Name of the movie")
    private String name;

    @ApiModelProperty(notes = "Brief summary of the movie's plot")
    private String summary;

    public Movie() {

    }

    public Movie(String name, String summary) {
        super();
        this.name = name;
        this.summary = summary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
