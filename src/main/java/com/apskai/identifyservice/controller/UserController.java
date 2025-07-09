package com.apskai.identifyservice.controller;

import java.util.ArrayList;
import java.util.List;

import com.apskai.identifyservice.dto.response.ApiResponse;
import com.apskai.identifyservice.dto.request.UserUpdateRequest;
import jakarta.validation.Valid;
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
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<User>();

        apiResponse.setResult(userService.createUser(request));
        apiResponse.setCode(1000);
        apiResponse.setMessage("Successfully!!");

        return apiResponse;
    }

    @GetMapping("/{userId}")
    public ApiResponse<User> getUser(@PathVariable String userId) {
        ApiResponse<User> apiResponse = new ApiResponse<User>();

        apiResponse.setResult(userService.getUser(userId));
        apiResponse.setCode(1000);
        apiResponse.setMessage("Successfully!!");

        return apiResponse;
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
    public ApiResponse<User> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<User>();

        apiResponse.setResult(userService.updateUser(userId, request));
        apiResponse.setCode(1000);
        apiResponse.setMessage("Successfully!!");

        return apiResponse;
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