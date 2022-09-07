package com.spring.mydiv.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.mydiv.Entity.User;

import antlr.collections.List;

/**
 * @author 12nov
 */
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByEmail(String Email);

    //List<User> findDevelopersByStatusEquals(StatusCode status);
}
