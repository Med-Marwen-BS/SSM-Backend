package com.example.userservice.Repo;


import com.example.userservice.Entity.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends MongoRepository<Team,String> {
    Optional<Team> findById(String id);
    Optional<List<Team>> findByNameAndCountry(String name, String country);
    Optional<Team> findByNameAndCountryAndIdNot(String name,String country,String id);
}
