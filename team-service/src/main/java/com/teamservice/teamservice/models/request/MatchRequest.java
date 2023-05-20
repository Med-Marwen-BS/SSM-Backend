package com.teamservice.teamservice.models.request;

import com.teamservice.teamservice.models.Match;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MatchRequest {
    private Match match;
    private List<String > playersIds;
}
