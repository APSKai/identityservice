package com.apskai.identifyservice.dto.response;

import java.time.LocalDate;
import java.util.Set;

import com.apskai.identifyservice.entity.Role;
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
    String firstname;
    String lastname;
    LocalDate DoB;
    Set<RoleResponse> roles;
}
