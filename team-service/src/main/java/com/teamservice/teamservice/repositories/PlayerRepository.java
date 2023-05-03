package com.teamservice.teamservice.repositories;

import com.teamservice.teamservice.models.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player,String> {
}
