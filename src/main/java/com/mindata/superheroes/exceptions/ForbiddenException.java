package com.mindata.superheroes.exceptions;

public class ForbiddenException extends RuntimeException {

    private static final long serialVersionUID = 4858892961752492872L;
    private static final String DESCRIPTION = "Forbidden Exception (403)";

    public ForbiddenException(String detail) {
        super(DESCRIPTION + ": " + detail);
    }
}
