package com.teamservice.teamservice.models.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TeamDTO {
    private String id ;
    private String name ;
    private String country;
    private String creatorId;

}
