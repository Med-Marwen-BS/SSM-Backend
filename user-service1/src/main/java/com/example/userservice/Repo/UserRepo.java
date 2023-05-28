package com.example.userservice.Repo;


import com.example.userservice.Entity.Team;
import com.example.userservice.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends MongoRepository<User,String> {


    Optional<User> findByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
    Optional<User> findByEmail(String email);

    List<User> findByEmailIn(List<String> emailList);
    List<User> findByUsernameIn(List<String> usernameList);

    List<User> findByTeamId(String teamId);

}
