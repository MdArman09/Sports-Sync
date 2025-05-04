package org.example.rtms.exceptions.NotFoundExceptions;

import org.example.rtms.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class CityNotFoundException extends ApiException {
    public CityNotFoundException(Long id){
        super("No city was found with id" + id, HttpStatus.NOT_FOUND);
    }

}
