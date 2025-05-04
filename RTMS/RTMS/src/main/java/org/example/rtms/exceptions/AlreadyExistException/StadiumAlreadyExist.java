package org.example.rtms.exceptions.AlreadyExistException;

import org.example.rtms.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class StadiumAlreadyExist extends ApiException {
    public StadiumAlreadyExist(String name) {
        super("Stadium with name: " + name + " already exists", HttpStatus.CONFLICT);

    }
}
