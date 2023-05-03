package com.teamservice.teamservice.services;

import com.teamservice.teamservice.models.Team;
import com.teamservice.teamservice.models.User;
import com.teamservice.teamservice.repositories.TeamRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    public Team addTeam(Team team){
        try {
            if(!team.checkRequiredFields()) throw new RuntimeException("check required fields failed");
            Team response = teamRepository.save(team);
            return response ;
        }catch (Exception e){
            return null;
        }
    }
    public Team updateTeam(Team team)
    {
        Optional<Team> optionalTeam = teamRepository.findById(team.getId());
        if(optionalTeam.isPresent()){
            Team toUpdate = optionalTeam.get() ;
            toUpdate.updateTeam(team);
            return toUpdate;
        }

        return null ;

    }
    public List<Team> getAllTeams(){
        return teamRepository.findAll() ;
    }

    public Team findById(String  id){
        Optional<Team> team = teamRepository.findById(id);

        if(team.isEmpty()) throw new RuntimeException("team not found");
        return team.get() ;
    }

}
