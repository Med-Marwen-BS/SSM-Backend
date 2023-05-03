package com.teamservice.teamservice.services;

import com.teamservice.teamservice.models.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.teamservice.teamservice.repositories.gameRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class gameService {
    private gameRepository gameRepository ;

    public gameEntity saveGame(gameEntity game){
        return gameRepository.save(game) ;
    }
    public List<gameEntity> getGames(){
        return gameRepository.findAll() ;
    }
}
