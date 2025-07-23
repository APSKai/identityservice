package com.apskai.identifyservice.mapper;

import com.apskai.identifyservice.dto.request.PermissionRequest;
import com.apskai.identifyservice.dto.response.PermissionResponse;
import com.apskai.identifyservice.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionResponse toPermissionResponse (Permission permission);
    Permission toPermission (PermissionResponse permissionResponse);
    Permission toPermission (PermissionRequest permissionRequest);
}
