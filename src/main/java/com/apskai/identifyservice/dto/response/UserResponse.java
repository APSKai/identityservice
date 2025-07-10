package com.apskai.identifyservice.dto.response;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

    String id;
    String username;
    String password;
    String firstname;
    String lastname;
    LocalDate DoB;
}
