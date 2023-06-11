package com.teamservice.teamservice.repositories;

import com.teamservice.teamservice.models.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification,String> {
    List<Notification> findByUserId(String userId);
}
