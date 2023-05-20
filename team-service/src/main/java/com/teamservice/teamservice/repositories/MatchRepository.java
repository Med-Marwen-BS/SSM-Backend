package com.teamservice.teamservice.repositories;

import com.teamservice.teamservice.models.Match;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends MongoRepository<Match ,String> {
    public List<Match> findByDateAndTeamId(LocalDate date,String teamId);
    public List<Match> findByDate(LocalDate date);
}
