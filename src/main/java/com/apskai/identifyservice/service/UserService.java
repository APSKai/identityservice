package com.apskai.identifyservice.service;

import java.util.HashSet;
import java.util.List;

import com.apskai.identifyservice.dto.response.UserResponse;
import com.apskai.identifyservice.enums.Role;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apskai.identifyservice.dto.request.UserCreationRequest;
import com.apskai.identifyservice.dto.request.UserUpdateRequest;
import com.apskai.identifyservice.entity.User;
import com.apskai.identifyservice.exception.AppException;
import com.apskai.identifyservice.exception.ErrorCode;
import com.apskai.identifyservice.mapper.UserMapper;
import com.apskai.identifyservice.repository.UserRepository;

@Slf4j
@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());

        user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public UserResponse getUser(String userId){
        return userMapper.toUserResponse(userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {
        userRepository.delete(userMapper.toUser(getUser(userId)));
    }
}