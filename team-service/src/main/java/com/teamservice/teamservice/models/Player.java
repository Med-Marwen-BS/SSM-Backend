package com.teamservice.teamservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(value = "Player")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Player {
    @Id
    private String id ;
    private String name ;
    private String email ;
    private LocalDate birthdate ;
    private String phone_number;
    private String height ;
    private String width ;

    private List<Stats> stats ;
    private Category category ;

    public boolean checkRequiredFields(){
        return !this.getName().isEmpty() && !this.getName().isBlank() && this.category!=null
                && !this.getEmail().isEmpty() && !this.getEmail().isBlank()
                && this.getBirthdate()!=null
                ;
    }
    public void updateCategory(Player player){
        if(!player.getName().isEmpty()&&!player.getName().isBlank()) this.setName(player.getName());
        if(!player.getEmail().isEmpty()&&!player.getEmail().isBlank()) this.setEmail(player.getEmail());
        if(!player.getPhone_number().isEmpty()&&!player.getPhone_number().isBlank()) this.setPhone_number(player.getPhone_number());
        if(!player.getHeight().isEmpty()&&!player.getHeight().isBlank()) this.setHeight(player.getHeight());
        if(!player.getWidth().isEmpty()&&!player.getWidth().isBlank()) this.setWidth(player.getWidth());
        if(player.getCategory() != null) this.setCategory(player.getCategory());
        if(player.getBirthdate() != null) this.setBirthdate(player.getBirthdate());
    }
}
