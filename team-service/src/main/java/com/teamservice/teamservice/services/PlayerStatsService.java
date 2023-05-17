package com.teamservice.teamservice.services;

import com.teamservice.teamservice.models.Player;
import com.teamservice.teamservice.models.PlayerStats;
import com.teamservice.teamservice.repositories.PlayerStatsRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlayerStatsService {
    private PlayerStatsRepository playerStatsRepository;

    public PlayerStats addPlayerStats(PlayerStats player){
        if(!player.checkRequiredFields()) throw new RuntimeException("check required fields failed");
        return playerStatsRepository.save(player) ;
    }
    public PlayerStats updatePlayer(PlayerStats player)
    {
        Optional<PlayerStats> optionalTeam = playerStatsRepository.findById(player.getId());
        if(optionalTeam.isPresent()){
            PlayerStats toUpdate = optionalTeam.get() ;
            toUpdate.updateCategory(player);
            return playerStatsRepository.save(toUpdate) ;
        }

        return null ;

    }
    public List<PlayerStats> getAllPlayers(){
        return playerStatsRepository.findAll() ;
    }

    public PlayerStats findById(String  id){
        Optional<PlayerStats> player = playerStatsRepository.findById(id);

        if(player.isEmpty()) throw new RuntimeException("player not found");
        return player.get() ;
    }

}
