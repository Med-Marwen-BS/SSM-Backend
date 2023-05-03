package com.teamservice.teamservice.controller;

import com.teamservice.teamservice.models.gameEntity;
import com.teamservice.teamservice.services.gameService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RefreshScope
@RestController
@AllArgsConstructor
@RequestMapping("/api/team-service")
public class gameController {
    private gameService service ;

    @PostMapping("/save")
    public gameEntity save(@RequestBody gameEntity game) {
        return service.saveGame(game);
    }

    @GetMapping("/getGames")
    public List<gameEntity> getGames() {
        return service.getGames();
    }
}
