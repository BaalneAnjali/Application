package com.example.APIFiles.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.APIFiles.Dto.roleDto;
import com.example.APIFiles.Entity.roleEntity;
import com.example.APIFiles.Repository.roleRepository;

@Service
public class roleServiceImpl implements roleService {
	 @Autowired
	    private roleRepository RoleRepository;

	@Override
	public roleEntity createRole(roleDto RoleDto) {
		roleEntity RoleEntity = new roleEntity();
		RoleEntity.setRoleName(RoleDto.getRoleName());
		RoleEntity.setRoleId(RoleDto.getRoleId());		
		return RoleRepository.save(RoleEntity);
	}

}
