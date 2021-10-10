package com.mindata.superheroes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user", nullable = false)
    private String user;

    @Column(name = "pass", nullable = false)
    private String pass;

    /**
     * @return el id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id el id a establecer
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return el name
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user el name a establecer
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return el pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass el pass a establecer
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

}
