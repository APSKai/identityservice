package com.apskai.identifyservice.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import com.apskai.identifyservice.dto.response.UserResponse;
import com.apskai.identifyservice.enums.Role;
import com.apskai.identifyservice.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
    RoleRepository roleRepository;

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());

//        user.setRoles(roles);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    // Sử dụng annotation EnableMethodSecurity
    // Với PreAuthorize thì những user có role được cho phép đi tiếp
//    @PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasAuthority('APPROVE_POST')")
    public List<UserResponse> getUsers() {
        log.info("In method getUsers");
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    // Với PostAuthorize thì method sẽ được gọi nhưng nếu điều kiện trong ngoặc không thỏa mãn
    // -> kết quả sẽ không được trả về và ngược lại
    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUser(String userId){
        log.info("In method getUser");
        return userMapper.toUserResponse(userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, request);

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {
        userRepository.delete(userMapper.toUser(getUser(userId)));
    }
}