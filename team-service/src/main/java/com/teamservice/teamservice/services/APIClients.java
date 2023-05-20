package com.teamservice.teamservice.services;

import com.teamservice.teamservice.models.request.UserResponse;
import com.teamservice.teamservice.models.request.getTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(url = "${user.service}" , value = "USER-SERVICE")
public interface APIClients {
    @GetMapping("/getByToken")
    getTokenResponse getByToken(@RequestHeader("Authorization") String bearerToken);
}
