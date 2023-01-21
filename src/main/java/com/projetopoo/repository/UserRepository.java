package com.projetopoo.repository;

import com.projetopoo.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Long> {
    List<User> findByEmail(String email);
}
