package com.example.APIFiles.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.APIFiles.Dto.roleDto;
import com.example.APIFiles.Entity.roleEntity;
import com.example.APIFiles.Service.roleService;

@RestController
@RequestMapping("/api/roles")
public class roleController {

	@Autowired
	public roleService RoleService;
	 
	 @PostMapping("/addRole")
	 public roleEntity addUser(@Validated @RequestBody roleDto roleDTO) {
	        return RoleService.createRole(roleDTO);
	 }
}
