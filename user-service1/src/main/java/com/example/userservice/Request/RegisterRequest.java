package com.example.userservice.Request;


import com.example.userservice.Enum.Role;
import com.example.userservice.Enum.Sexe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private Role role;

    private Sexe sexe;

   // private Region region;

}
