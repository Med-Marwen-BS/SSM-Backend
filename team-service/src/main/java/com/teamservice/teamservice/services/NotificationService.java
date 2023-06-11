package com.teamservice.teamservice.services;

import com.teamservice.teamservice.Enum.NotificationStatus;
import com.teamservice.teamservice.models.Notification;
import com.teamservice.teamservice.models.Stats;
import com.teamservice.teamservice.repositories.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    public List<Notification> getList(String userId){

        return notificationRepository.findByUserId(userId) ;
    }

    public String changeStatus(String notificationId){
        Notification notification =notificationRepository.findById(notificationId).orElseThrow() ;
        notification.setStatus(NotificationStatus.READ);
        notificationRepository.save(notification);
        return "success" ;
    }
}
