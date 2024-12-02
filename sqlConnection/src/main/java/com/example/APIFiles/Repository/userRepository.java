package com.example.APIFiles.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.example.APIFiles.Entity.userEntity;

public interface userRepository extends JpaRepository<userEntity , Long>  {
    Optional<userEntity> findBySecurityKey(String securityKey);
    Optional<userEntity> findByUserName(@Param("username") String userName);
}
