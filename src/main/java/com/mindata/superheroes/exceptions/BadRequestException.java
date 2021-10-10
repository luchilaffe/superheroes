package com.mindata.superheroes.exceptions;

public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -7780706623001801344L;
    private static final String DESCRIPTION = "Bad Request Exception (400)";

    public BadRequestException(String detail) {
        super(DESCRIPTION + ": " + detail);
    }
}
