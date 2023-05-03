package com.teamservice.teamservice.repositories;

import com.teamservice.teamservice.models.Match;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MatchRepository extends MongoRepository<Match ,String> {
}
