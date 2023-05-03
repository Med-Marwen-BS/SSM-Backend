package com.teamservice.teamservice.services;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url = "${second_service.name}" , value = "second-service")
public interface APIClients {

}
