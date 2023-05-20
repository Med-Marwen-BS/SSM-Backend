package com.teamservice.teamservice.controller;

import com.teamservice.teamservice.models.Match;
import com.teamservice.teamservice.services.MatchService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RefreshScope
@RestController
@AllArgsConstructor
@RequestMapping("/api/team-service/match")
public class MatchController {
    private MatchService matchService;

    @PostMapping("/save")
    public Match save(@RequestHeader("Authorization") String token,@RequestBody Match stats) {
        return matchService.addMatch(stats,token);
    }
    @PutMapping("/update")
    public Match update(@RequestBody Match stats) {
        return matchService.updatePlayer(stats);
    }

    @GetMapping("/getAll")
    public List<Match> getMatchs() {
        return matchService.getAll();
    }
    @GetMapping("/get/{id}")
    public Match getMatchById(@PathVariable("id") String id) {
        return matchService.findById(id);
    }

    @GetMapping("/getByDate/{teamId}/{date}")
    public List<Match> getMatchById(@PathVariable("teamId") String teamId,@PathVariable("date") LocalDate date) {
        return matchService.findByDate(date,teamId);
    }

}
