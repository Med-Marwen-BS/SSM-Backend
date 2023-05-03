package com.teamservice.teamservice.models;

import com.teamservice.teamservice.utility.Helper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(value = "PlayerStats")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PlayerStats {
    @Id
    private String id ;
    private Player player ;
    private Stats stats ;
//    private LocalDate date ;
//    private String opponent ;


    public boolean checkRequiredFields(){
        return  this.getPlayer()!=null
                && this.getStats()!=null
                ;
    }
    public void updateCategory(PlayerStats playerStats){
        if(playerStats.getPlayer() != null) this.setPlayer(playerStats.getPlayer());
        if(playerStats.getStats() != null) this.setStats(playerStats.getStats());
        //if(playerStats.getDate() != null) this.setDate(playerStats.getDate());
        //if(Helper.isNotEmptyString(this.getOpponent())) this.setOpponent(playerStats.getOpponent());
    }
}
