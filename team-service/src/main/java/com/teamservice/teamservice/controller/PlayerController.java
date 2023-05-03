package com.teamservice.teamservice.controller;

import com.teamservice.teamservice.models.Player;
import com.teamservice.teamservice.services.PlayerService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
@AllArgsConstructor
@RequestMapping("/api/team-service/player")
public class PlayerController {
    private PlayerService playerService;

    @PostMapping("/save")
    public Player save(@RequestBody Player player) {
        return playerService.addPlayer(player);
    }
    @PutMapping("/update")
    public Player update(@RequestBody Player player) {
        return playerService.updatePlayer(player);
    }

    @GetMapping("/getAll")
    public List<Player> getTeams() {
        return playerService.getAllPlayers();
    }
    @GetMapping("/get/{id}")
    public Player getPlayerById(@PathVariable("id") String id) {

        return playerService.findById(id);
    }

}
