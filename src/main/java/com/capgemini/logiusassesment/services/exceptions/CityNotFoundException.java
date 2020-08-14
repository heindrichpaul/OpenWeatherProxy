package com.capgemini.logiusassesment.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CityNotFoundException extends Exception {
    public CityNotFoundException(String name) {
        super("The city ("+name+") was not found");
    }
}
