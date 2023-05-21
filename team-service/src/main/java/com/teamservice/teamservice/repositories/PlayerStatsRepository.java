package com.teamservice.teamservice.repositories;

import com.teamservice.teamservice.Enum.Status;
import com.teamservice.teamservice.models.Category;
import com.teamservice.teamservice.models.PlayerStats;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PlayerStatsRepository extends MongoRepository<PlayerStats,String> {
    List<PlayerStats> findByMatchId(String  matchId);
    List<PlayerStats> findByPlayerId(String  playerId);
}
