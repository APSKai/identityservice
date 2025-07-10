package com.apskai.identifyservice.controller;

import java.util.List;

import com.apskai.identifyservice.dto.response.ApiResponse;
import com.apskai.identifyservice.dto.request.UserUpdateRequest;
import com.apskai.identifyservice.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
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


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @PostMapping
    public UserResponse createUser(@RequestBody @Valid UserCreationRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable("userId") String userId) {
        return userService.getUser(userId);
    }

    @GetMapping
    public ApiResponse<List<User>> getUsers() {
        ApiResponse<List<User>> apiResponse = new ApiResponse<>();

        apiResponse.setResult(userService.getUsers());
        apiResponse.setCode(1000);
        apiResponse.setMessage("Successfully!!");

        return apiResponse;
    }

    @PutMapping("/{userId}")
    public UserResponse updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    public ApiResponse deleteUser(@PathVariable String userId) {
        ApiResponse apiResponse = new ApiResponse<>();

        userService.deleteUser(userId);
        apiResponse.setCode(1000);
        apiResponse.setMessage("User has been deleted!!");

        return apiResponse;
    }
}