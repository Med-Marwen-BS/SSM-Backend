package com.teamservice.teamservice.services;

import com.teamservice.teamservice.models.Team;
import com.teamservice.teamservice.models.User;
import com.teamservice.teamservice.models.request.TeamDTO;
import com.teamservice.teamservice.repositories.TeamRepository;
import com.teamservice.teamservice.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepo userRepo;

    public Team addTeam(TeamDTO team,String username){
        try {
            Team toSave=Team.builder().name(team.getName()).country(team.getCountry()).build();
            if(userRepo.findById(team.getCreatorId()).isPresent()){
                toSave.setCreatorId(team.getCreatorId());
            }else {
                throw new RuntimeException("user not found");
            }
            if(!toSave.checkRequiredFields()) throw new RuntimeException("check required fields failed");
            Optional<List<Team> > teamList = teamRepository.findByNameAndCountry(toSave.getName(),toSave.getCountry());
            if(teamList.isPresent()){
                if(teamList.get().size()>0)
                    throw new RuntimeException("name and country already exist");
            }
            //toSave.setCreator(user);
            Optional<User> userOptional = userRepo.findByUsername(username);
            if(userOptional.isEmpty()) throw new RuntimeException("user not found");

            User user = userOptional.get();
            //if(user.getTeam()!=null) throw new RuntimeException("already have a team");
            if(user.getTeam().getId()!=null) throw new RuntimeException("already have a team");
            Team response = teamRepository.save(toSave);
            user.setTeam(response);
            userRepo.save(user);
            return response ;
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }
    public Team updateTeam(Team team)
    {
        //TODO: Test
        Optional<Team> optionalTeam = teamRepository.findById(team.getId());
        if(optionalTeam.isPresent()){
            Optional<List<Team> > teamList = teamRepository.findByNameAndCountry(team.getName(),team.getCountry());
            if(teamList.isPresent()){
                ;
                if(!teamList.get().stream().allMatch(t->t.getId().equals(team.getId())))
                    throw new RuntimeException("name and country already exist");
            }

            Team toUpdate = optionalTeam.get() ;
            toUpdate.updateTeam(team);
            return teamRepository.save(toUpdate);
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

    public String delete(String teamId){
        teamRepository.deleteById(teamId);
        return "success";
    }


}
