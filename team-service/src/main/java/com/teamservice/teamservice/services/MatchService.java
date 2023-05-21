package com.teamservice.teamservice.services;

import com.teamservice.teamservice.Enum.Status;
import com.teamservice.teamservice.models.*;
import com.teamservice.teamservice.models.request.MatchRequest;
import com.teamservice.teamservice.models.request.getTokenResponse;
import com.teamservice.teamservice.repositories.MatchRepository;
import com.teamservice.teamservice.repositories.PlayerRepository;
import com.teamservice.teamservice.repositories.PlayerStatsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MatchService {
    private MatchRepository matchRepository;
    private APIClients apiClients;
    private PlayerStatsRepository playerStatsRepository;
    private PlayerRepository playerRepository;

    public Match addMatch(MatchRequest matchRequest, String token){

        Match match = matchRequest.getMatch();
        getTokenResponse userResponse = apiClients.getByToken(token);
        match.setCreator(userResponse.getData().getId());

       // playerStatsRepository.find
        if(!match.checkRequiredFields()) throw new RuntimeException("check required fields failed");

        Match newMatch = matchRepository.save(match) ;
        List<PlayerStats> playerStats = new ArrayList<>();
        //List<Player> players1 = playerRepository.findByCategoryId(match.getCategory().getId());
        List<Player> players = playerRepository.findAllById(matchRequest.getPlayersIds());
        players.forEach(player -> {
            playerStats.add(new PlayerStats(newMatch,player));
        });
        playerStatsRepository.saveAll(playerStats);
        return newMatch ;
    }
    public Match updatePlayer(Match match)
    {
        Optional<Match> optionalMatch = matchRepository.findById(match.getId());
        if(optionalMatch.isPresent()){
            Match toUpdate = optionalMatch.get() ;
            toUpdate.updateMatch(match);
            return matchRepository.save(toUpdate) ;
        }

        return null ;

    }

    public Match updateScore(Match match)
    {
        Optional<Match> optionalMatch = matchRepository.findById(match.getId());
        if(optionalMatch.isPresent()){
            Match toUpdate = optionalMatch.get() ;
            toUpdate.updateScore(match);
            return matchRepository.save(toUpdate) ;
        }

        return null ;

    }

    public Match statusLive(String  matchId)
    {
        Optional<Match> optionalMatch = matchRepository.findById(matchId);
        if(optionalMatch.isPresent()){
            Match toUpdate = optionalMatch.get() ;
            toUpdate.setStatus(Status.Live);
            return matchRepository.save(toUpdate) ;
        }

        return null ;

    }
    public Match statusFinished(String  matchId)
    {
        Optional<Match> optionalMatch = matchRepository.findById(matchId);
        if(optionalMatch.isPresent()){
            Match toUpdate = optionalMatch.get() ;
            toUpdate.setStatus(Status.Finished);
            return matchRepository.save(toUpdate) ;
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

    public List<Match> findByDate(LocalDate date, String teamId){
        List<Match> match = matchRepository.findByDateAndTeamId(date,teamId);

        if(match == null) throw new RuntimeException("match not found");
        return match ;
    }

}
