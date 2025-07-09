package com.apskai.identifyservice.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserUpdateRequest {
    @Size(min = 8, message = "PASSWORD_INVALID")
    private String password;
    private String lastname;
    private String firstname;
    private LocalDate DoB;
}