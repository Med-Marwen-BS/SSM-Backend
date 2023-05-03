package com.teamservice.teamservice.models;

import com.teamservice.teamservice.utility.Helper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(value = "Match")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Match {
    @Id
    private String id ;
    private List<PlayerStats> playerStats ;
    private LocalDate date ;
    private String opponent ;


    public boolean checkRequiredFields(){
        return Helper.isNotEmptyString(this.getOpponent())
                && this.getDate()!=null
                && this.getPlayerStats()!=null
                ;
    }
    public void updateCategory(Match match){
        if(Helper.isNotEmptyString(this.getOpponent())) this.setOpponent(match.getOpponent());
        if(match.getPlayerStats() != null) this.setPlayerStats(match.getPlayerStats());
        if(match.getDate() != null) this.setDate(match.getDate());
    }
}
