package com.mindata.superheroes.dto;

public class SuperHeroesDto {

    Long id;
    String name;

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
