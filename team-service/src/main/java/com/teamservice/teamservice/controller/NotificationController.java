package com.teamservice.teamservice.controller;

import com.teamservice.teamservice.models.Notification;
import com.teamservice.teamservice.models.gameEntity;
import com.teamservice.teamservice.services.NotificationService;
import com.teamservice.teamservice.services.gameService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/team-service/notification")
public class NotificationController {
    private NotificationService service ;

    @GetMapping("/getAll/{userId}")
    public List<Notification> save(@PathVariable("userId") String  userId) {
        return service.getList(userId);
    }

    @GetMapping("/changeStatus/{notificationId}")
    public String  getGames(@PathVariable("notificationId") String  notificationId) {
        return service.changeStatus(notificationId);
    }
}
