package org.example.rtms.exceptions.NotFoundExceptions;

import org.example.rtms.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class StadiumNotFoundException extends ApiException {
    public StadiumNotFoundException(Long id){
        super("No Stadium was found with id " + id, HttpStatus.NOT_FOUND);
    }


}
