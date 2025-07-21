package com.apskai.identifyservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error!!"),
    USER_EXISTED(1001, "User existed!!"),
    INVALID_KEY(1002, "Invalid massage key!!"),
    USERNAME_INVALID(1003, "Username must be at least 3 characters!!"),
    PASSWORD_INVALID(1004, "Password must be at least 8 characters!!"),
    USER_NOT_EXISTED(1005, "User not existed!!"),
    UNAUTHENTICATED(1006, "Unauthenticated!!"),
    FAILED_TO_GENERATE_TOKEN(1007, "Failed to generate Token")
    ;

    private int code;
    private String message;
}