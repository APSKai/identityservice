package com.apskai.identifyservice.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
// De verify token xem co hop le khong va check xem co dung la token do he thong nay issue khong
public class IntrospectRequest {
    String token;
}
