package chiba.dev.userservice.Model.Entity.Param;

import chiba.dev.userservice.Model.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailBodyParam {

    private String username;
    private String message;
}
