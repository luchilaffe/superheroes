package com.mindata.superheroes.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mindata.superheroes.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUser(String user);

}
