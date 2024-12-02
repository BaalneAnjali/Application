package com.example.APIFiles.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.APIFiles.Dto.userDto;
import com.example.APIFiles.Entity.userEntity;
import com.example.APIFiles.Repository.userRepository;

@Service
public class userServiceImpl  implements userService {
	 @Autowired
	    private userRepository UserRepository;
	 
	  @Autowired(required=true)
	    private emailService mailSender;

	    // Method to send the email
		/*
		 * @Override public void sendEmail(String to, String subject, String body) {
		 * SimpleMailMessage message = new SimpleMailMessage();
		 * System.out.print("entered into send email method"); message.setTo(to);
		 * message.setSubject(subject); message.setText(body);
		 * System.out.print("to , subject and body " +to+ "" +subject+ "" +body);
		 * mailSender.send(message); }
		 */
	@Override
	public userEntity createUser(userDto userDTO) {
		userEntity userentity = new userEntity();
	            String securityKey = UUID.randomUUID().toString();
	            userentity.setSecurityKey(securityKey);
		//roleEntity RoleEntity = new roleEntity();
		userentity.setUserId(userDTO.getUserId());
		userentity.seteMail(userDTO.geteMail());
		userentity.setPassWord(userDTO.getPassWord());
		userentity.setUserName(userDTO.getUserName());
		userentity.setUserRoleId(userDTO.getUserRoleId());
		
        userEntity savedUser = UserRepository.save(userentity);
        sendEmail(userDTO.geteMail());
		
		/*
		 * String emailBody = String.format(
		 * "Hello %s,\n\nThank you for registering. Your security key is : %s.\n\nRegards,\nYour Team"
		 * , userDTO.getUserName(), securityKey );
		 * System.out.print(" entered into 52 "); try {
		 * System.out.print(" entered into sendEmail  method ");
		 * mailSender.sendEmail(userDTO.geteMail(), " Your Registration Security Key ",
		 * emailBody);
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } System.out.print(" mail is " +userDTO.geteMail());
		 * System.out.print(" entered into 53 "); // Send the email
		 */		
        

        return savedUser;

	}
	    public String sendEmail(String Email) { 
			/*
			 * userDto userdto = new userDto(); String Email = userdto.geteMail();
			 */
		 System.out.println("Mail id Is : " +Email); 
		 try {
		 mailSender.sendEmailByMail(Email, "Test Subject", "Test Email Body");
		 return "Email sent successfully!"; 
		 }
		 catch (Exception e) {
		 e.printStackTrace(); return "Failed to send email.";
		 }
		 }
	
		@Override
		public List<userEntity> getAllEntities() {
			// TODO Auto-generated method stub
	        return UserRepository.findAll();
		}
		
		@Override
		public Optional<userEntity> getUserById(Long userid)
		{
			return UserRepository.findById(userid);
		}
		@Override
		public userEntity updateUser(userEntity existingUser) {
			// TODO Auto-generated method stub
			return UserRepository.save(existingUser);
		}

		@Override
		public boolean deleteUserById(Long userid) {
			// TODO Auto-generated method stub
			 if (UserRepository.existsById(userid)) 
			 {
				 UserRepository.deleteById(userid);
		            return true;
		        } 
			 else {
		            return false;
		        }
		}

		@Override
		public void deleteAllUsers() {
			UserRepository.deleteAll();
		}

		@Override
		public Optional<userEntity> getUserBySecurityKey(String securityKey) {
			// TODO Auto-generated method stub
			return UserRepository.findBySecurityKey(securityKey);
		}
		
		 public boolean validateUser(String userName, String password) {
			    System.out.println(" user Name is : " +userName+ " password is : "  +password);
		        return UserRepository.findByUserName(userName)
		                .map(userEntity -> userEntity.getPassWord().equals(password)) // In production, use hashed passwords
		                .orElse(false);
		    }
}
