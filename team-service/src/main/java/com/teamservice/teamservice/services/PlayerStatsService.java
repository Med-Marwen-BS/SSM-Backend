package com.teamservice.teamservice.services;

import com.teamservice.teamservice.Enum.Status;
import com.teamservice.teamservice.models.Category;
import com.teamservice.teamservice.models.PlayerStats;
import com.teamservice.teamservice.models.Team;
import com.teamservice.teamservice.models.request.StatsPlayerResponse;
import com.teamservice.teamservice.models.request.getTokenResponse;
import com.teamservice.teamservice.repositories.CategoryRepository;
import com.teamservice.teamservice.repositories.PlayerStatsRepository;
import com.teamservice.teamservice.repositories.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;



@Service
@AllArgsConstructor
public class PlayerStatsService {
    private PlayerStatsRepository playerStatsRepository;
    private TeamRepository teamRepository;
    private CategoryRepository categoryRepository;
    private final MongoTemplate mongoTemplate;


    public PlayerStats addPlayerStats(PlayerStats player){
        if(!player.checkRequiredFields()) throw new RuntimeException("check required fields failed");
        return playerStatsRepository.save(player) ;
    }
    public List<PlayerStats> updatePlayer(List<PlayerStats> psList)
    {
        List<PlayerStats> toUpdateList = new ArrayList<>();
        psList.forEach(player -> {
            Optional<PlayerStats> optionalTeam = playerStatsRepository.findById(player.getId());
            if(optionalTeam.isPresent()){
                PlayerStats toUpdate = optionalTeam.get() ;
                toUpdate.updatePlayerStats(player);
                toUpdateList.add(toUpdate);
                //return playerStatsRepository.save(toUpdate) ;
            }
        });

        return playerStatsRepository.saveAll(toUpdateList);


    }
    public List<PlayerStats> getAllPlayers(){
        return playerStatsRepository.findAll() ;
    }

    public List<PlayerStats> findByMatchId(String  matchId){
        List<PlayerStats> players = playerStatsRepository.findByMatchId(matchId);

        if(players.isEmpty()) throw new RuntimeException("players not found");
        return players ;
    }

    public List<PlayerStats> findByPlayerId(String  playerId){
        List<PlayerStats> players = playerStatsRepository.findByPlayerId(playerId);


        if(players.isEmpty()) throw new RuntimeException("players not found");
        return players.stream().filter(playerStats -> playerStats.getMatch().getStatus().equals(Status.Finished)).collect(Collectors.toList());
    }

    public List<StatsPlayerResponse> mostGoalsInEveryCategory(String teamId){
        List<Category> categories = categoryRepository.findByTeamId(teamId);
        List<StatsPlayerResponse> result = new ArrayList<>();

        for (Category c : categories){
            List<PlayerStats> ps = playerStatsRepository.findAll().stream().filter(data-> Objects.equals(c.getId(), data.getPlayer().getCategory().getId())).toList();
            if(ps.size()!=0)
                result.add(this.findMaxGoalsByPlayerAndCategory(ps,c.getName()));
        }

        return result;

    }

    public StatsPlayerResponse findMaxGoalsByPlayerAndCategory(List<PlayerStats> ps,String categoryId){

        //ps = playerStatsRepository.findAll();
        Map<String ,PlayerStats> result = new HashMap<>();

        for (PlayerStats p : ps){
            if(!result.containsKey(p.getPlayer().getId())){
                result.put(p.getPlayer().getId(),p);
            }else {

                PlayerStats find = result.get(p.getPlayer().getId());
                find.setGoals(find.getGoals()+p.getGoals());
                find.setMinutes(find.getMinutes()+p.getMinutes());
                find.setSaves(find.getSaves()+p.getSaves());
                find.setShots(find.getShots()+p.getShots());
                result.put(p.getPlayer().getId(),find);
            }
        }
        PlayerStats goals = null;
        PlayerStats shots = null;
        PlayerStats saves = null;
        PlayerStats minutes = null;

        for(PlayerStats p : result.values()){
            //Goals
            if(goals ==null) goals = p;
            else if(goals.getGoals()<p.getGoals()){
                goals = p;
            }

            //shots
            if(shots ==null) shots = p;
            else if(shots.getShots()<p.getShots()){
                shots = p;
            }
            //saves
            if(saves ==null) saves = p;
            else if(saves.getSaves()<p.getSaves()){
                saves = p;
            }

            //minutes
            if(minutes ==null) minutes = p;
            else if(minutes.getMinutes()<p.getMinutes()){
                minutes = p;
            }
        }

        StatsPlayerResponse sp = new StatsPlayerResponse(categoryId,shots,goals,saves,minutes);



        return sp;


        //return result.getMappedResults();
    }

}
