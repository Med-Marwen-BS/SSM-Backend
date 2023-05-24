package com.teamservice.teamservice.controller;

import com.teamservice.teamservice.models.Player;
import com.teamservice.teamservice.models.PlayerStats;
import com.teamservice.teamservice.models.request.StatsPlayerResponse;
import com.teamservice.teamservice.services.PlayerService;
import com.teamservice.teamservice.services.PlayerStatsService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RefreshScope
@RestController
@AllArgsConstructor
@RequestMapping("/api/team-service/playerStats")
public class PlayerStatsController {
    private PlayerStatsService playerService;

    @PostMapping("/save")
    public PlayerStats save(@RequestBody PlayerStats player) {
        return playerService.addPlayerStats(player);
    }
    @PutMapping("/update")
    public List<PlayerStats> update(@RequestBody List<PlayerStats> player) {
        return playerService.updatePlayer(player);
    }

    @GetMapping("/getAll")
    public List<PlayerStats> getTeams() {
        return playerService.getAllPlayers();
    }
    @GetMapping("/get/{id}")
    public List<PlayerStats> getPlayerByMatchId(@PathVariable("id") String id) {

        return playerService.findByMatchId(id);
    }

    @GetMapping("/getByPlayer/{id}")
    public List<PlayerStats> getByPlayerId(@PathVariable("id") String id) {

        return playerService.findByPlayerId(id);
    }

    @GetMapping("/getStats/{teamId}")
    public List<StatsPlayerResponse> findMaxGoalsByPlayerAndCategory(@PathVariable("teamId") String teamId){
        return playerService.mostGoalsInEveryCategory(teamId);
    }

}
