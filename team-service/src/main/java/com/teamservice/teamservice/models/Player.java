package com.teamservice.teamservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

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
    private String firstName ;
    private String lastName ;
    private String email ;
    private LocalDate birthdate ;
    private String phone_number;
    private String height ;
    private String width ;
    @DocumentReference
    private List<Stats> stats ;
    @DocumentReference
    private Category category ;

    public boolean checkRequiredFields(){
        return !this.getFirstName().isEmpty() && !this.getFirstName().isBlank()
                && !this.getLastName().isEmpty() && !this.getLastName().isBlank()
                && this.category!=null
                && !this.getEmail().isEmpty() && !this.getEmail().isBlank()
                && this.getBirthdate()!=null
                ;
    }
    public void updateCategory(Player player){
        if(!player.getFirstName().isEmpty()&&!player.getFirstName().isBlank()) this.setFirstName(player.getFirstName());
        if(!player.getLastName().isEmpty()&&!player.getLastName().isBlank()) this.setLastName(player.getLastName());
        if(!player.getEmail().isEmpty()&&!player.getEmail().isBlank()) this.setEmail(player.getEmail());
        if(!player.getPhone_number().isEmpty()&&!player.getPhone_number().isBlank()) this.setPhone_number(player.getPhone_number());
        if(!player.getHeight().isEmpty()&&!player.getHeight().isBlank()) this.setHeight(player.getHeight());
        if(!player.getWidth().isEmpty()&&!player.getWidth().isBlank()) this.setWidth(player.getWidth());
        if(player.getCategory() != null) this.setCategory(player.getCategory());
        if(player.getBirthdate() != null) this.setBirthdate(player.getBirthdate());
    }
}
