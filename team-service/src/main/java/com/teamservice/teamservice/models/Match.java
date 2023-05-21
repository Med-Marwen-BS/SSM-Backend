package com.teamservice.teamservice.models;

import com.teamservice.teamservice.Enum.Status;
import com.teamservice.teamservice.utility.Helper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Document(value = "Match")
@AllArgsConstructor
@Data
@Builder
public class Match {
    @Id
    private String id ;
    @DocumentReference
    private List<PlayerStats> playerStats ;
    @DocumentReference
    private Category category ;
    @DocumentReference
    private Team team ;
    private String  creator ;
    private LocalDate date ;
    private LocalTime time ;
    private Status status ;
    private String opponent ;

    private int opponentScore;
    private int myScore;



    public Match(){
        date=LocalDate.now();
        status=Status.NotStarted;
        opponentScore=0;
        myScore=0;
    }
    public boolean checkRequiredFields(){
        return Helper.isNotEmptyString(this.getOpponent())
                && Helper.isNotEmptyString(this.getCreator())
                && this.getDate()!=null
                && this.getTime()!=null
                && this.getCategory()!=null
                && this.getTeam()!=null
                ;
    }
    public void updateMatch(Match match){
        if(Helper.isNotEmptyString(this.getOpponent())) this.setOpponent(match.getOpponent());
        if(match.getDate() != null) this.setDate(match.getDate());
        if(match.getTime() != null) this.setTime(match.getTime());
        //if(match.getPlayerStats() != null) this.setPlayerStats(match.getPlayerStats());
        //if(match.getDate() != null) this.setDate(match.getDate());
    }

    public void updateScore(Match match){

        this.setMyScore(match.getMyScore());
        this.setOpponentScore(match.getOpponentScore());
        //if(match.getPlayerStats() != null) this.setPlayerStats(match.getPlayerStats());
        //if(match.getDate() != null) this.setDate(match.getDate());
    }
}
