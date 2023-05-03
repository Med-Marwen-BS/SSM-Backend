package com.teamservice.teamservice.repositories;

import com.teamservice.teamservice.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category ,String> {
}
