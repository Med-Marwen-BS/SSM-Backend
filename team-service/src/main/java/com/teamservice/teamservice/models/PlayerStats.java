package com.teamservice.teamservice.models;

import com.teamservice.teamservice.utility.Helper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;

@Document(value = "PlayerStats")
@AllArgsConstructor
@Data
@Builder
public class PlayerStats {
    @Id
    private String id ;
    @DocumentReference(lazy = true)
    private Player player ;
    private Integer shots ;
    private Integer goals ;
    private Integer saves ;
    private Integer minutes ;
    @DocumentReference
    private Match match ;
//    private LocalDate date ;
//    private String opponent ;


    public PlayerStats(Match match,Player player){
        this.match=match;
        this.player=player;
        shots=0;
        goals=0;
        saves=0;
        minutes=0;
    }
    public boolean checkRequiredFields(){
        return  this.getPlayer()!=null
                ;
    }
    public void updateCategory(PlayerStats playerStats){
        if(playerStats.getPlayer() != null) this.setPlayer(playerStats.getPlayer());
        //if(playerStats.getDate() != null) this.setDate(playerStats.getDate());
        //if(Helper.isNotEmptyString(this.getOpponent())) this.setOpponent(playerStats.getOpponent());
    }
}
