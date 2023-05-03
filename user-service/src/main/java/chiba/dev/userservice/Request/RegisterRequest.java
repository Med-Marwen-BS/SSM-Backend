package chiba.dev.userservice.Request;

import chiba.dev.userservice.Enum.Role;
import chiba.dev.userservice.Enum.Sexe;
import chiba.dev.userservice.Model.Entity.Region;
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
