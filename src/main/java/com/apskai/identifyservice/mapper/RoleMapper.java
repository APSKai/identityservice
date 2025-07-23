package com.apskai.identifyservice.mapper;

import com.apskai.identifyservice.dto.request.RoleRequest;
import com.apskai.identifyservice.dto.response.RoleResponse;
import com.apskai.identifyservice.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role toRole (RoleRequest roleRequest);

    RoleResponse toRoleResponse (Role role);
}
