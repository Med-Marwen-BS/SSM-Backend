package com.teamservice.teamservice.repositories;


import com.teamservice.teamservice.models.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository<Team,String> {
}
