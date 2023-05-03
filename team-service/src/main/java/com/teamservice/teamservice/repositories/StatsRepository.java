package com.teamservice.teamservice.repositories;

import com.teamservice.teamservice.models.PlayerStats;
import com.teamservice.teamservice.models.Stats;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StatsRepository extends MongoRepository<Stats,String> {
}
