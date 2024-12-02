package com.example.APIFiles.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.util.Properties;




@Service
public class emailService 
{
	
	/*
	 * @Autowired(required=true) private JavaMailSender mailSender;
	 * 
	 * // Method to send the email public void sendEmailwithpaasword(String to,
	 * String subject, String body) { SimpleMailMessage message = new
	 * SimpleMailMessage(); System.out.print("entered into send email method ");
	 * message.setTo(to); message.setSubject(subject); message.setText(body);
	 * System.out.print(" to , subject and body " +to+ " " +subject+ " " +body);
	 * mailSender.send(message); }
	 */
	  
	  @Value("${spring.mail.username}")
	    private String username;

	    @Value("${spring.mail.password}")
	    private String password;

	    @Value("${spring.mail.host}")
	    private String host;

	    @Value("${spring.mail.port}")
	    private int port;

	    public void sendEmailByMail(String to, String subject, String body) throws MessagingException {

	        // Set up the mail server properties
	        Properties properties = new Properties();
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", port);
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");
	        properties.put("mail.smtp.ssl.trust", host);

	        // Create a Session with the mail server details
	        Session session = Session.getInstance(properties, new Authenticator() {
	            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	        });

	        // Create a new email message
	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(username));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	        message.setSubject(subject);
	        message.setText(body);

	        // Send the email
	        Transport.send(message);
	    }

}
