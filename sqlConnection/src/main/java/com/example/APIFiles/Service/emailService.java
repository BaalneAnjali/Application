package com.example.APIFiles.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;

@Service
public class emailService {

	
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
	        	//request.addHeader("Authorization", "Bearer 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");
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
}
