package com.mindata.superheroes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mindata.superheroes.model.SuperHeroes;

@Repository
public interface SuperHeroesRepository extends JpaRepository<SuperHeroes, Long> {

}
