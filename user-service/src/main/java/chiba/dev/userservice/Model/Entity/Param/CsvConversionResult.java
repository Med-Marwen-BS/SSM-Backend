package chiba.dev.userservice.Model.Entity.Param;

import chiba.dev.userservice.Model.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CsvConversionResult {

    private List<User> userList;
    private CsvResult csvResult;
    private Map<User,Integer> indexedUsers;
}
