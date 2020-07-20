package com.gemasoftware.springjwtmysql.repos;

import com.gemasoftware.springjwtmysql.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {

    Optional<User> findByUserName(String userName);

    //Checks if user exists by Username
    Boolean existsByUserName(String userName);

    //Checks if user exists by email
    Boolean existsByUserEmail(String userEmail);
}
