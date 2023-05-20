package com.teamservice.teamservice.repositories;

import com.teamservice.teamservice.models.Match;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends MongoRepository<Match ,String> {
    public List<Match> findByDateEqualsAndTeamId(LocalDate date,String teamId);
}
