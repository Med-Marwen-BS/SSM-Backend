package com.teamservice.teamservice.models;

import com.teamservice.teamservice.utility.Helper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(value = "Team")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Team {
    @Id
    private String id ;
    private String name ;
    private String country;

    private String creatorId;
//    @DocumentReference(lazy = true)
    //private User creator;
//    @DocumentReference(lazy = true) private List<User> users;

//    @DBRef
//    private List<Category> categories ;

    public boolean uniqueNameAndCountry(){
        return true;
    }
    public void updateTeam(Team team){
        if(!team.getName().isEmpty()&&!team.getName().isBlank()) this.setName(team.getName());
        if(!team.getCreatorId().isEmpty()&&!team.getCreatorId().isBlank()) this.setCreatorId(team.getCreatorId());
        if(!team.getCountry().isEmpty()&&!team.getCountry().isBlank()) this.setCountry(team.getCountry());
    }
    public boolean checkRequiredFields(){
        return Helper.isNotEmptyString(name)&&Helper.isNotEmptyString(country)&&Helper.isNotEmptyString(creatorId);
    }
}
