package com.example.userservice.controller;

import com.example.userservice.Request.RegisterRequest;
import com.example.userservice.Service.AuthService;
import com.example.userservice.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RefreshScope
@RestController
@AllArgsConstructor
@RequestMapping("/api/user-service")
public class gameController {

    private final UserService userService;
    private final AuthService authService;
    @GetMapping("/test")
    public String  test() {
        return "works";
    }

    @PostMapping("/reg")
    public ResponseEntity<RegisterRequest> register1(@RequestBody RegisterRequest registerRequest){
        System.out.println(registerRequest.getFirstName());
        return ResponseEntity.ok(registerRequest);
    }
}
