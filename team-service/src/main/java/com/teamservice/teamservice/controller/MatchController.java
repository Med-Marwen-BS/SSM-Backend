package com.teamservice.teamservice.controller;

import com.teamservice.teamservice.models.Match;
import com.teamservice.teamservice.models.request.MatchRequest;
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
    public Match save(@RequestHeader("Authorization") String token,@RequestBody MatchRequest matchRequest) {
        return matchService.addMatch(matchRequest,token);
    }
    @PutMapping("/update")
    public Match update(@RequestBody Match match) {
        return matchService.updatePlayer(match);
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

    @PutMapping("/updateScore")
    public Match updateScore(@RequestBody Match match) {
        return matchService.updateScore(match);
    }
    @PutMapping("/updateToLive/{id}")
    public Match updateToLive(@RequestHeader("Authorization") String token,@PathVariable("id") String  match) {
        return matchService.statusLive(token,match);
    }
    @PutMapping("/updateToFinished/{id}")
    public Match updateToFinished(@PathVariable("id") String  match) {
        return matchService.statusFinished(match);
    }

}
