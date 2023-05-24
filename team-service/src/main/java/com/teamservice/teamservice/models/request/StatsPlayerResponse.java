package com.teamservice.teamservice.models.request;

import com.teamservice.teamservice.models.PlayerStats;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatsPlayerResponse {
    private String categoryName ;
    private PlayerStats shots ;
    private PlayerStats goals ;
    private PlayerStats saves ;
    private PlayerStats minutes ;
}
