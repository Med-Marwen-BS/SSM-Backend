package com.teamservice.teamservice.services;

import com.teamservice.teamservice.Enum.Status;
import com.teamservice.teamservice.models.PlayerStats;
import com.teamservice.teamservice.repositories.PlayerStatsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PlayerStatsService {
    private PlayerStatsRepository playerStatsRepository;

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

}
