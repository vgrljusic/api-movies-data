package com.vgrljusic.apimoviesdata.Models;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such movie in database, try searching online...")
public class MovieNotFoundException extends RuntimeException{

    public MovieNotFoundException(String id) {
        super("No such movie in database, try searching online... " + id);
    }
}
