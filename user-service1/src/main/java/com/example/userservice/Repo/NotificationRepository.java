package com.example.userservice.Repo;

import com.example.userservice.Entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification,String> {
    List<Notification> findByUserId(String userId);
}
