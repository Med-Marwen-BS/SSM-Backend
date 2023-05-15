package com.teamservice.teamservice.services;

import com.teamservice.teamservice.models.Player;
import com.teamservice.teamservice.models.Team;
import com.teamservice.teamservice.repositories.PlayerRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PlayerService {
    private PlayerRepository playerRepository;

    public Player addPlayer(Player player){
        if(player.checkRequiredFields()) throw new RuntimeException("check required fields failed");
        return playerRepository.save(player) ;
    }
    public Player updatePlayer(Player player)
    {
        Optional<Player> optionalTeam = playerRepository.findById(player.getId());
        if(optionalTeam.isPresent()){
            Player toUpdate = optionalTeam.get() ;
            toUpdate.updateCategory(player);
            return playerRepository.save(toUpdate) ;
        }

        return null ;

    }
    public List<Player> getAllPlayers(){
        return playerRepository.findAll() ;
    }

    public Player findById(String  id){
        Optional<Player> player = playerRepository.findById(id);

        if(player.isEmpty()) throw new RuntimeException("player not found");
        return player.get() ;
    }

}
