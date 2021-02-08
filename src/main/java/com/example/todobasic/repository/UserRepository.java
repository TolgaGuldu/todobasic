package com.example.todobasic.repository;

import com.example.todobasic.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created on February, 2021
 *
 * @author tolga
 */

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> getByUsername(String username);
    boolean existsByUsername(String username);
}
