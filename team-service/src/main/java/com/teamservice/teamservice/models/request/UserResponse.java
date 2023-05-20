package com.teamservice.teamservice.models.request;

import com.teamservice.teamservice.Enum.Role;
import com.teamservice.teamservice.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private Role role;

}
