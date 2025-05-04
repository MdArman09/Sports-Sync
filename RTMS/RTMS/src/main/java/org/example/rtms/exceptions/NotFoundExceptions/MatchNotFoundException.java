package org.example.rtms.exceptions.NotFoundExceptions;

import org.example.rtms.exceptions.ApiException;
import org.springframework.http.HttpStatus;

import java.util.UUID;

public class MatchNotFoundException extends ApiException {
    public MatchNotFoundException(UUID uuid){
        super("No match was found with UUID " + uuid, HttpStatus.NOT_FOUND);
    }
}

