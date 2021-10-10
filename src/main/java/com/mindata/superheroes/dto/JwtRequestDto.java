package com.mindata.superheroes.dto;

import java.io.Serializable;

/**
 * DTO to receive information for user login.
 * 
 * @author carlos.lafferriere
 *
 */
public class JwtRequestDto implements Serializable {

    private static final long serialVersionUID = -7868907340117827129L;

    private String user;
    private String pass;

    /**
     * No Args Constructor
     */
    public JwtRequestDto() {

    }

    /**
     * All Args Constructor
     * 
     * @param username of the user
     * @param password of the user
     */
    public JwtRequestDto(String username, String password) {
        this.setUser(username);
        this.setPass(password);
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String username) {
        this.user = username;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String password) {
        this.pass = password;
    }
}
