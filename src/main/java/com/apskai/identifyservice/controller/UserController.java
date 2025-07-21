package com.apskai.identifyservice.controller;

import java.util.List;

import com.apskai.identifyservice.dto.response.ApiResponse;
import com.apskai.identifyservice.dto.request.UserUpdateRequest;
import com.apskai.identifyservice.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apskai.identifyservice.dto.request.UserCreationRequest;
import com.apskai.identifyservice.entity.User;
import com.apskai.identifyservice.service.UserService;


@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.createUser(request))
                .build();
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUser(@PathVariable("userId") String userId) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getUser(userId))
                .build();
    }

    @GetMapping
    public ApiResponse<List<User>> getUsers() {

        // Authentication của người dùng đang login được lưu trong SecurityContextHolder
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority ->
                log.info(grantedAuthority.getAuthority()));

        return ApiResponse.<List<User>>builder()
                .result(userService.getUsers())
                .build();
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
                .build();
    }

    @DeleteMapping("/{userId}")
    public ApiResponse<User> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ApiResponse.<User>builder()
                .code(1000)
                .message("User has been deleted!!!")
                .build();
    }
}