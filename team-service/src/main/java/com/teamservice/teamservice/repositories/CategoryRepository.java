package com.teamservice.teamservice.repositories;

import com.teamservice.teamservice.models.Category;
import com.teamservice.teamservice.models.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends MongoRepository<Category ,String> {
    Optional<List<Category>> findByNameIgnoreCaseAndTeamId(String name, Team team);
    List<Category> findByTeamId(String teamId);
}
