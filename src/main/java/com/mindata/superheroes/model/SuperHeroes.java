package com.mindata.superheroes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "superheroes")
public class SuperHeroes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

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
    public String getName() {
        return name;
    }

    /**
     * @param name el name a establecer
     */
    public void setName(String name) {
        this.name = name;
    }

}
