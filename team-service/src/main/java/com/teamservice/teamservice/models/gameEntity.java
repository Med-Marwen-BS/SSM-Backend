package com.teamservice.teamservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "game")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class gameEntity {
    @Id
    String id ;
    String first_team;
    String second_team;
}
