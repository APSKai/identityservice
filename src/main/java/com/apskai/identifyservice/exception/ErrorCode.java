package com.apskai.identifyservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error!!", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Invalid message key!!", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed!!", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least 3 characters!!", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004, "Password must be at least 8 characters!!", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed!!", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated!!", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "Unauthorized!!", HttpStatus.FORBIDDEN),
    FAILED_TO_GENERATE_TOKEN(1008, "Failed to generate Token", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_DOB(1009, "Invalid date of birth", HttpStatus.BAD_REQUEST)
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;
}