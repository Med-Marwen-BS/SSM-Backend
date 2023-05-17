package com.teamservice.teamservice.controller;

import com.teamservice.teamservice.models.Team;
import com.teamservice.teamservice.models.request.TeamDTO;
import com.teamservice.teamservice.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("*")
@RestController
@RequestMapping("/api/team-service/team")
public class TeamController {
    @Autowired
    private TeamService teamService ;

    @PostMapping("/save/{username}")
    public Team save(@RequestBody TeamDTO team,@PathVariable("username") String username) {
            return teamService.addTeam(team,username);
    }
    @PutMapping("/update")
    public Team update(@RequestBody Team team) {
        return teamService.updateTeam(team);
    }

    @GetMapping("/getAll")
    public List<Team> getTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/get/{id}")
    public Team getTeamById(@PathVariable("id") String id) {

        return teamService.findById(id);
    }

}
