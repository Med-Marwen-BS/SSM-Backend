package com.teamservice.teamservice.services;

import com.teamservice.teamservice.models.Stats;
import com.teamservice.teamservice.repositories.StatsRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class StatsService {
    private StatsRepository statsRepository;

    public Stats addPlayerStats(Stats stats){
        if(stats.checkRequiredFields()) throw new RuntimeException("check required fields failed");
        return statsRepository.save(stats) ;
    }
    public Stats updatePlayer(Stats stats)
    {
        Optional<Stats> optionalTeam = statsRepository.findById(stats.getId());
        if(optionalTeam.isPresent()){
            Stats toUpdate = optionalTeam.get() ;
            toUpdate.updateCategory(stats);
            return toUpdate;
        }

        return null ;

    }
    public List<Stats> getAll(){
        return statsRepository.findAll() ;
    }

    public Stats findById(String  id){
        Optional<Stats> stats = statsRepository.findById(id);

        if(stats.isEmpty()) throw new RuntimeException("stats not found");
        return stats.get() ;
    }

}
