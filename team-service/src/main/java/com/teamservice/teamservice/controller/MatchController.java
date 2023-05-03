package com.teamservice.teamservice.controller;

import com.teamservice.teamservice.models.Match;
import com.teamservice.teamservice.services.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RefreshScope
@RestController
@AllArgsConstructor
@RequestMapping("/api/team-service/match")
public class MatchController {
    private MatchService matchService;

    @PostMapping("/save")
    public Match save(@RequestBody Match stats) {
        return matchService.addMatch(stats);
    }
    @PutMapping("/update")
    public Match update(@RequestBody Match stats) {
        return matchService.updatePlayer(stats);
    }

    @GetMapping("/getAll")
    public List<Match> getTeams() {
        return matchService.getAll();
    }
    @GetMapping("/get/{id}")
    public Match getPlayerById(@PathVariable("id") String id) {
        return matchService.findById(id);
    }

}
