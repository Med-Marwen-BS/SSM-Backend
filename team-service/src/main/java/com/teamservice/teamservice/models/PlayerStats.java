package com.teamservice.teamservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(value = "PlayerStats")
@AllArgsConstructor
@NoArgsConstructor
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
    public void updatePlayerStats(PlayerStats playerStats){
        //Todo: update all fields
        //if(playerStats.getPlayer() != null) this.setPlayer(playerStats.getPlayer());
        //if(playerStats.getDate() != null) this.setDate(playerStats.getDate());
        //if(Helper.isNotEmptyString(this.getOpponent())) this.setOpponent(playerStats.getOpponent());
        if(playerStats.getShots()!=null) this.setShots(playerStats.getShots());
        if(playerStats.getGoals()!=null) this.setGoals(playerStats.getGoals());
        if(playerStats.getSaves()!=null) this.setSaves(playerStats.getSaves());
        if(playerStats.getMinutes()!=null) this.setMinutes(playerStats.getMinutes());
        if(playerStats.getMinutes()!=null) this.setMinutes(playerStats.getMinutes());
        if(playerStats.getMinutes()!=null) this.setMinutes(playerStats.getMinutes());
    }
}
