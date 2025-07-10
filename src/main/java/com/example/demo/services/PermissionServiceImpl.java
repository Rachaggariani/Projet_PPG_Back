package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.PermissionDTO;
import com.example.demo.entities.Permission;
import com.example.demo.entities.User;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.repositories.UserRepository;

@Service
public class PermissionServiceImpl implements PermissionService{
	
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Override
    public void savePermissions(List<PermissionDTO> permissionsDto) {
        for (PermissionDTO dto : permissionsDto) {
            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Permission permission = permissionRepository.findByUserAndInterfaceName(user, dto.getInterfaceName())
                    .orElse(new Permission());

            permission.setUser(user);
            permission.setInterfaceName(dto.getInterfaceName());
            permission.setCanAdd(dto.isCanAdd());
            permission.setCanEdit(dto.isCanEdit());
            permission.setCanView(dto.isCanView());
            permission.setCanDelete(dto.isCanDelete());

            permissionRepository.save(permission);
        }
    }

    @Override
    public List<PermissionDTO> getPermissionsForUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Permission> perms = permissionRepository.findByUser(user);

        return perms.stream().map(p -> {
            PermissionDTO dto = new PermissionDTO();
            dto.setUserId(userId);
            dto.setInterfaceName(p.getInterfaceName());
            dto.setCanView(p.isCanView());
            dto.setCanAdd(p.isCanAdd());
            dto.setCanEdit(p.isCanEdit());
            dto.setCanDelete(p.isCanDelete());
            return dto;
        }).toList();
    }

}
