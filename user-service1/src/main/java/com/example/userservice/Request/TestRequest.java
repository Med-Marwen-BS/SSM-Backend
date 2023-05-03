package com.example.userservice.Request;

import com.example.userservice.Enum.Role;
import com.example.userservice.Enum.Sexe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestRequest {

    private String firstName;

    private String lastName;
}
