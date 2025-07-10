package com.apskai.identifyservice.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponse {
    //true neu cung cap authen dung, false neu cung cap authen sai
    boolean authenticated;
}
