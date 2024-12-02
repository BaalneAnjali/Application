package com.example.APIFiles.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

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
	  @Value("${sendgrid.api.key}")
	    private String sendGridApiKey;

	    @Value("${sendgrid.email.from}")
	    private String fromEmail;

	  public void sendEmail(String to, String subject, String body) throws IOException {
          System.out.print(" entered into sendEmail  method in emailService class %s.\\n\\n ");
	        Email from = new Email(fromEmail); // Your verified email
	          System.out.print(" From Mail is " +fromEmail);
	        Email To = new Email(to);
	          System.out.print(" From Mail is " +to);
	        Content emailContent = new Content("text/plain", body);
	        Mail mail = new Mail(from, subject, To, emailContent);

	        SendGrid sg = new SendGrid("sendGridApiKey");
	          System.out.print(" From Mail is " +sendGridApiKey);
	        Request request = new Request();
	        

	        try {
	            request.setMethod(Method.POST);
	            request.setEndpoint("mail/send");
	            request.setBody(mail.build());
	            Response response = sg.api(request);
	            System.out.println(" Response: " + response.getStatusCode());
	            System.out.println("Response Body: " + response.getBody());
	            System.out.println("Response Headers: " + response.getHeaders());
	            System.out.println("Key Sent successfully");
	        } catch (IOException ex) {
	            throw ex;
	        }
	    }
	  
	  
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
