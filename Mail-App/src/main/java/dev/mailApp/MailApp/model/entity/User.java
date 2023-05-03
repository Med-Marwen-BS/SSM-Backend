package dev.mailApp.MailApp.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;


    public String getFullName(){
        return this.lastName+" "+this.firstName;
    }

}
