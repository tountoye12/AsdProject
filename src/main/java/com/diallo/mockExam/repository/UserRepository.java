package com.diallo.mockExam.repository;

import com.diallo.mockExam.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    /**
     * findByName method is used to retrieve a user by their username.
     * It returns an Optional of UserInfo, which will be empty if no user is found.
     */

    Optional<User> findByUsername(String username);
}
