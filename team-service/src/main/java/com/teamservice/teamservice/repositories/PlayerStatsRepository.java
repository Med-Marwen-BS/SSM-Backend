package com.teamservice.teamservice.repositories;

import com.teamservice.teamservice.models.Category;
import com.teamservice.teamservice.models.PlayerStats;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerStatsRepository extends MongoRepository<PlayerStats,String> {
}
