package com.teamservice.teamservice.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(value = "Category")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Category {
    @Id
    private String id ;
    private String name ;

    private Team team ;

//    private List<User> backroom_staff ;
//    private List<Player> players ;
    public boolean checkRequiredFields(){
        return !this.getName().isEmpty() && !this.getName().isBlank() && team!=null ;
    }
    public void updateCategory(Category category){
        if(!category.getName().isEmpty()&&!category.getName().isBlank()) this.setName(category.getName());
        if(category.getTeam() != null) this.setTeam(category.getTeam());
    }

}
