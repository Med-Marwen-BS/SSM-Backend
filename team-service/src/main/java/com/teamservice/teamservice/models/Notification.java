package com.teamservice.teamservice.models;

import com.teamservice.teamservice.Enum.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(value = "Notification")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Notification {
    @Id
    private String id ;
    private String message ;
    private String userId ;
    private NotificationStatus status;

    //    private List<User> backroom_staff ;
//    private List<Player> players ;
//    public boolean checkRequiredFields(){
//        return !this.getName().isEmpty() && !this.getName().isBlank() && team!=null ;
//    }
//    public void updateCategory(Category category){
//        if(!category.getName().isEmpty()&&!category.getName().isBlank()) this.setName(category.getName());
//        if(category.getTeam() != null) this.setTeam(category.getTeam());
//    }
}
