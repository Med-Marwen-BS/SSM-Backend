package com.example.userservice.Entity.Param;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeamToUserBody {
    private String email;
    private String teamId;
}
