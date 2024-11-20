package com.example.APIFiles.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.APIFiles.Dto.userDto;
import com.example.APIFiles.Entity.userEntity;
import com.example.APIFiles.Service.userService;

@RestController
@RequestMapping("/api/users")
public class userController implements ErrorController {
	
	 @Autowired
	 public userService UserService;
	 
	 @PostMapping("/addUser")
	 public userEntity addUser(@Validated @RequestBody userDto userDTO) {
	        return UserService.createUser(userDTO);
	 }
	 @PostMapping("/register")
	    public ResponseEntity<String> registerUser(@RequestBody userDto userDTO) {
	        try {
				userEntity registeredUser = UserService.createUser(userDTO);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  // Register user and send email
	        return ResponseEntity.ok("User registered successfully! A security key has been sent to your email.");
	    }
	 
	 @GetMapping("/all")
	    public List<userEntity> getAllEntities() {
	    	return  UserService.getAllEntities();	    }
	    
	 @GetMapping("/by-security-key/{securityKey}")
	 public ResponseEntity<userEntity> getUserDetials(@PathVariable("securityKey") String securityKey) {
		    System.out.println("Received securityKey: " +securityKey);
		 userEntity user = UserService.getUserBySecurityKey(securityKey).orElse(null);
		    System.out.println("mail id is	: " +user.geteMail());
		 return ResponseEntity.ok(user);
	     }
	 @PutMapping("/{userid}")
	    public ResponseEntity<userEntity> updateUserDetilas(@PathVariable Long userid, @RequestBody userEntity UserEntity) {
	        Optional<userEntity> userOptional = UserService.getUserById(userid);
	        
	        if (userOptional.isPresent()) {
	        	userEntity existingUser = userOptional.get();
	            
	            // Update fields, but don't modify created_at (to keep it unchanged)
	        	//existingUser.setUserId(UserEntity.getUserId());
	        	existingUser.setUserName(UserEntity.getUserName());
	        	existingUser.seteMail(UserEntity.geteMail());
	            existingUser.setPassWord(UserEntity.getPassWord());
	            existingUser.setCreatedAt(LocalDateTime.now());
	            // Save the updated user
	            userEntity updatedUser = UserService.updateUser(existingUser);

	            // Return the updated user as a response
	            return ResponseEntity.ok(updatedUser);
	        } else {
	            return ResponseEntity.notFound().build();  // Return 404 if user not found
	        }
	    }
	    
	    
	    @DeleteMapping("/delete/{userId}")
	    public  ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId)
	    {
			boolean isDeleted = UserService.deleteUserById(userId);
	    	
			 if (isDeleted) {
		            return ResponseEntity.ok("User deleted successfully");
		        } else {
		            return ResponseEntity.status(404).body("User not found");
		        }
		    }
	    
	    @DeleteMapping("/deleteAll")
	    public ResponseEntity<String> deleteAllUsers()
	    {
	    	UserService.deleteAllUsers();
	    	return ResponseEntity.ok("All users are deleted");
	    }
	    @Controller
	    public class UserController {
	    	 @GetMapping("/home")
		        public String showHomePage() {
		            return "home";
		        }
	        @GetMapping("/register")
	        public String showRegisterPage() {
	            return "register";
	        }
	        @PostMapping("/register")
	        public String handleRegister(String userName, String password) {
	            // Perform registration logic
	            // Store user in the database, etc.
	            return "redirect:/login";  // Redirect to login after successful registration
	        }
	        @GetMapping("/login")
		    public String showLoginPage() {
		    	System.out.println("entered into the method");
		        return "login";  // This should return the name of your login HTML page
		    }
	        @PostMapping("/login")
		    public String login(@RequestParam String userName, @RequestParam String password, Model model) {
		        if (UserService.validateUser(userName, password)) {
		            return "application"; // Redirect to home.html
		        } else {
		            model.addAttribute("error", "Invalid username or password");
		            return "login"; // Stay on login.html with an error message
		        }
		    }
	    }

	    @RequestMapping("/error")
	    public String handleError() {
	        return "error";  // Return to error.html page when an error occurs
	    }

	    public String getErrorPath() {
	        return "/error";
	    }
}