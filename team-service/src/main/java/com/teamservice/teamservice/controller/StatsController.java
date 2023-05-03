package com.teamservice.teamservice.controller;

import com.teamservice.teamservice.models.Stats;
import com.teamservice.teamservice.services.StatsService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
@AllArgsConstructor
@RequestMapping("/api/team-service/stats")
public class StatsController {
    private StatsService statsService;

    @PostMapping("/save")
    public Stats save(@RequestBody Stats stats) {
        return statsService.addPlayerStats(stats);
    }
    @PutMapping("/update")
    public Stats update(@RequestBody Stats stats) {
        return statsService.updatePlayer(stats);
    }

    @GetMapping("/getAll")
    public List<Stats> getTeams() {
        return statsService.getAll();
    }
    @GetMapping("/get/{id}")
    public Stats getPlayerById(@PathVariable("id") String id) {
        return statsService.findById(id);
    }

}
