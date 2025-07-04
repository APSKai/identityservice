package com.apskai.identifyservice.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreationRequest {
    private String username;

    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
    private String firstname;
    private String lastname;
    private LocalDate DoB;
}
