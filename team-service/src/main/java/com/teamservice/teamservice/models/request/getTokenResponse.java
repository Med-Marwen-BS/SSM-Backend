package com.teamservice.teamservice.models.request;

import com.teamservice.teamservice.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class getTokenResponse {

    private UserResponse data;
    private String status;
}
