package com.campos.ecommerce.services.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;

public class DatabaseException extends RuntimeException{

    public DatabaseException(String msg) {
        super(msg);
    }
}
