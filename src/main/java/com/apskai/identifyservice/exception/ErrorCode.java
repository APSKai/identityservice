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
    ;

    private int code;
    private String message;
}