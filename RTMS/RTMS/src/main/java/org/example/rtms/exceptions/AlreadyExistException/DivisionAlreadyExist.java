package org.example.rtms.exceptions.AlreadyExistException;

import org.example.rtms.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class DivisionAlreadyExist extends ApiException {
    public DivisionAlreadyExist(String name){
        super("Division with name: " + name + " already exists", HttpStatus.CONFLICT);
    }
}
