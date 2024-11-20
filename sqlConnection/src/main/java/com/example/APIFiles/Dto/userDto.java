package com.example.APIFiles.Dto;

import java.time.LocalDateTime;

public class userDto {

private Long id;
private Long userId;
private String userName;
private String eMail;
private String passWord;
private LocalDateTime createdAt;
private Long userRoleId;
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
public String getSecurityKey() {
	return securityKey;
}
public void setSecurityKey(String securityKey) {
	this.securityKey = securityKey;
}


}
