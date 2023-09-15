package com.example.tiendas.Excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNotFoundException extends RuntimeException {
	
	public ClienteNotFoundException (String message) {
        super(message);
    }
}
