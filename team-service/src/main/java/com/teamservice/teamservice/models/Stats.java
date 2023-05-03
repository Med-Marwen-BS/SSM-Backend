package com.teamservice.teamservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(value = "Stats")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Stats {
    @Id
    private String id ;
    //private Match match ;
    private Integer shots ;
    private Integer goals ;
    private Integer saves ;

    public boolean checkRequiredFields(){
        //TODO:
//        return this.getShots() && this.category!=null
//                && !this.getEmail().isEmpty() && !this.getEmail().isBlank()
//                && this.getBirthdate()!=null
//                ;
        return true ;
    }
    public void updateCategory(Stats player){
        //TODO:

//        if(!player.getName().isEmpty()&&!player.getName().isBlank()) this.setName(player.getName());
//        if(!player.getEmail().isEmpty()&&!player.getEmail().isBlank()) this.setEmail(player.getEmail());
//        if(!player.getPhone_number().isEmpty()&&!player.getPhone_number().isBlank()) this.setPhone_number(player.getPhone_number());
//        if(!player.getHeight().isEmpty()&&!player.getHeight().isBlank()) this.setHeight(player.getHeight());
//        if(!player.getWidth().isEmpty()&&!player.getWidth().isBlank()) this.setWidth(player.getWidth());
//        if(player.getCategory() != null) this.setCategory(player.getCategory());
//        if(player.getBirthdate() != null) this.setBirthdate(player.getBirthdate());
    }
}
