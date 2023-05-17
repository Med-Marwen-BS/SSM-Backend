package com.teamservice.teamservice.repositories;


import com.teamservice.teamservice.models.Team;
import com.teamservice.teamservice.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends MongoRepository<Team,String> {
    Optional<Team> findById(String id);
    Optional<List<Team>> findByNameAndCountry(String name, String country);
    Optional<Team> findByNameAndCountryAndIdNot(String name,String country,String id);
}
