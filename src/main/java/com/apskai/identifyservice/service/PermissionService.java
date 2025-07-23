package com.apskai.identifyservice.service;

import com.apskai.identifyservice.dto.request.PermissionRequest;
import com.apskai.identifyservice.dto.response.PermissionResponse;
import com.apskai.identifyservice.entity.Permission;
import com.apskai.identifyservice.mapper.PermissionMapper;
import com.apskai.identifyservice.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {

    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create (PermissionRequest request) {
        Permission permission = permissionMapper.toPermission(request);
        return permissionMapper.toPermissionResponse(permissionRepository.save(permission));
    }

    public List<PermissionResponse> getAll() {
        var permissions = permissionRepository.findAll();
        return permissions.stream()
                .map(permissionMapper::toPermissionResponse)
                .toList();
    }

    public void delete (String permissionName) {
        permissionRepository.deleteById(permissionName);
    }
}
