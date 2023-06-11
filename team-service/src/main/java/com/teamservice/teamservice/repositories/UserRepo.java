package com.teamservice.teamservice.repositories;

import com.teamservice.teamservice.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends MongoRepository<User,String> {


    Optional<User> findByUsername(String username);
    Optional<List<User>> findByTeamId(String teamId);
    Optional<User> findById(String id);
    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}
