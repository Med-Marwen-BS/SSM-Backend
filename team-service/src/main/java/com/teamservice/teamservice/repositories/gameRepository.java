package com.teamservice.teamservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.teamservice.teamservice.models.gameEntity;

public interface gameRepository extends MongoRepository<gameEntity,String> {
}
