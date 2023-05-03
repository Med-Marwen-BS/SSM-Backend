package dev.mailApp.MailApp.model.Param;

import dev.mailApp.MailApp.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailParam {

    private String email;
    private String fullName;
    private String username;
    private String sexe;
    private String type;
    private String resetPass;
    private String message;
    private User sender;
}
