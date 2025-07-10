package com.example.demo.services;

import java.util.List;


import com.example.demo.dtos.PermissionDTO;
import com.example.demo.entities.Permission;

public interface PermissionService {
	void savePermissions(List<PermissionDTO> permissionsDto);
	List<PermissionDTO> getPermissionsForUser(Long id);
}
