package com.apskai.identifyservice.service;

import java.util.List;

import com.apskai.identifyservice.dto.request.UserUpdateRequest;
import com.apskai.identifyservice.exception.AppException;
import com.apskai.identifyservice.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apskai.identifyservice.dto.request.UserCreationRequest;
import com.apskai.identifyservice.entity.User;
import com.apskai.identifyservice.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request) {
        User user = new User();

        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setDoB(request.getDoB());

        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("ErrorCode.UNCATEGORIZED_EXCEPTION"));
    }

    public User updateUser(String userId, UserUpdateRequest request) {
        User user = getUser(userId);

        user.setPassword(request.getPassword());
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setDoB(request.getDoB());

        return userRepository.save(user);
    }

    public void deleteUser(String userId) {
        User user = getUser(userId);
        userRepository.delete(user);
    }
}