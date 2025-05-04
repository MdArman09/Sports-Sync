package org.example.rtms.exceptions.AlreadyExistException;

import org.example.rtms.exceptions.ApiException;
import org.springframework.http.HttpStatus;

public class TeamAlreadyExist extends ApiException {
    public TeamAlreadyExist(String name){
        super("Team with name: " + name + " already exists", HttpStatus.CONFLICT);
    }

}
