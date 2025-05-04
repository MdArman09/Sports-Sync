package org.example.rtms.exceptions.NotFoundExceptions;

import org.example.rtms.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class DivisionNotFoundException extends ApiException {
    public DivisionNotFoundException(Long id){
        super("No division was found with id " + id, HttpStatus.NOT_FOUND);
    }

}
