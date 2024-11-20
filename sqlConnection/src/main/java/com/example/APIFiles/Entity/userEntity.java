package com.example.APIFiles.Entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "users")
public class userEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NonNull
    @Column(nullable = false, unique = true)
    private Long userId;
    
    @NonNull
    @Column(nullable = false)
    private String userName;
    
    private String eMail;
    
    @NonNull
    @Column(nullable = false)
    private String passWord;
    
    @Column(name = "created_at" , columnDefinition = "DATETIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    //@UpdateTimestamp (we can use this annotation to update the time)
    private LocalDateTime createdAt;
    
    private Long userRoleId;
	
    @Column(nullable = false, unique = true)
    private String securityKey;
    
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String geteMail() {
		return eMail;
	}



	public void seteMail(String eMail) {
		this.eMail = eMail;
	}



	public String getPassWord() {
		return passWord;
	}



	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Long getUserRoleId() {
		return userRoleId;
	}



	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}



	@PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }



	public String getSecurityKey() {
		return securityKey;
	}



	public void setSecurityKey(String securityKey) {
		this.securityKey = securityKey;
	}
	
	
    
}
