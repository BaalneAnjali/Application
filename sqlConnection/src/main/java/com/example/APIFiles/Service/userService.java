package com.example.APIFiles.Service;

import java.util.List;
import java.util.Optional;

import com.example.APIFiles.Dto.userDto;
import com.example.APIFiles.Entity.userEntity;

public interface userService {
    List<userEntity> getAllEntities();
	Optional<userEntity> getUserBySecurityKey(String securityKey);
	Optional<userEntity> getUserById(Long userid);
	userEntity createUser(userDto userDTO);
	userEntity updateUser(userEntity existingUser);
	boolean deleteUserById(Long userid);
	void deleteAllUsers();
	boolean validateUser(String userName, String password);
	//void sendEmail(String to, String subject, String body);
	
}