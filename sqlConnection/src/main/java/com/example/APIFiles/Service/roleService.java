package com.example.APIFiles.Service;

import com.example.APIFiles.Dto.roleDto;
import com.example.APIFiles.Entity.roleEntity;

public interface roleService {
	
	roleEntity createRole(roleDto RoleDto);

}
