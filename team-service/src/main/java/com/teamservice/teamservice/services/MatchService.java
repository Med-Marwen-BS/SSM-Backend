package com.teamservice.teamservice.services;

import com.teamservice.teamservice.models.Match;
import com.teamservice.teamservice.repositories.MatchRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class MatchService {
    private MatchRepository matchRepository;

    public Match addMatch(Match match){
        if(match.checkRequiredFields()) throw new RuntimeException("check required fields failed");
        return matchRepository.save(match) ;
    }
    public Match updatePlayer(Match match)
    {
        Optional<Match> optionalMatch = matchRepository.findById(match.getId());
        if(optionalMatch.isPresent()){
            Match toUpdate = optionalMatch.get() ;
            toUpdate.updateCategory(match);
            return toUpdate;
        }

        return null ;

    }
    public List<Match> getAll(){
        return matchRepository.findAll() ;
    }

    public Match findById(String  id){
        Optional<Match> match = matchRepository.findById(id);

        if(match.isEmpty()) throw new RuntimeException("match not found");
        return match.get() ;
    }

}
