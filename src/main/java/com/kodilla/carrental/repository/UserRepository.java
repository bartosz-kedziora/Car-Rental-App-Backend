package com.kodilla.carrental.repository;

import com.kodilla.carrental.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhoneNumber(int phoneNumber);

    Boolean existsByEmail(String email);
}

