package chiba.dev.userservice.Model.Entity.Param;

import chiba.dev.userservice.Enum.CsvStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CsvResult {

    private Integer numberOfFailure = 0;
    private Integer numberOfCreated = 0;
    private Integer numberOfUpdated = 0;

    private List<Integer> failureRows = new ArrayList<>();


    public void updateResult(CsvStatus status,Integer row){
        switch (status){
            case CREATED :
                this.numberOfCreated++;
                break;
            case UPDATED:
                this.numberOfUpdated++;
                break;
            case FAILURE:
                this.numberOfFailure++;
                failureRows.add(row);
                break;
        }
    }
}
