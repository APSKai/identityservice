package com.apskai.identifyservice.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserUpdateRequest {
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
    private String lastname;
    private String firstname;
    private LocalDate DoB;
}
