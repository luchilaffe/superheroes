package com.mindata.superheroes.dto;

import java.io.Serializable;

/**
 * DTO to response information after user login.
 * 
 * @author carlos.lafferriere
 *
 */
public class JwtResponseDto implements Serializable {

    private static final long serialVersionUID = 1562051004275438740L;
    private final String jwtToken;

    public JwtResponseDto(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getToken() {
        return this.jwtToken;
    }
}
