package com.apskai.identifyservice.mapper;

import com.apskai.identifyservice.dto.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.apskai.identifyservice.dto.request.UserCreationRequest;
import com.apskai.identifyservice.dto.request.UserUpdateRequest;
import com.apskai.identifyservice.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
