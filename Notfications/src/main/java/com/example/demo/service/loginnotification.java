package com.example.demo.service;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserDetailsModel;

@Service
public class loginnotification {
	
	//@Autowired
		
	@Value("${com.mail}")
	private String emailId;

	@Value("${com.mail.password}")
	private String password;

	// sends mail to newly created  user
	public void sendmail(String mail ) throws AddressException, MessagingException, IOException {
		Properties prop = new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailId, password);
			}
		});
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(emailId, false));
		
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
		msg.setSubject(" Clean Car Wash");
		msg.setContent("Dear User,  Thank you for registering to Clean Car Wash",
				"text/html");
		
		Transport.send(msg);
	}
	
	// sends mail to user when order placed
		public void orderplaced(String mail ) throws AddressException, MessagingException, IOException {
			Properties prop = new Properties();
			prop.put("mail.smtp.auth", "true");
			prop.put("mail.smtp.starttls.enable", "true");
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.port", "587");

			Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(emailId, password);
				}
			});
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(emailId, false));
			
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
			msg.setSubject(" Clean Car Wash");
			msg.setContent("Dear Customer,  Your Order has been placed and it will be processed soon ",
					"text/html");
			
			Transport.send(msg);
		}
	
		// sends mail to user when order completed
				public void ordercompleted(String mail ) throws AddressException, MessagingException, IOException {
					Properties prop = new Properties();
					prop.put("mail.smtp.auth", "true");
					prop.put("mail.smtp.starttls.enable", "true");
					prop.put("mail.smtp.host", "smtp.gmail.com");
					prop.put("mail.smtp.port", "587");

					Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
						@Override
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(emailId, password);
						}
					});
					Message msg = new MimeMessage(session);
					msg.setFrom(new InternetAddress(emailId, false));
					
					msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
					msg.setSubject(" Clean Car Wash");
					msg.setContent("Dear Customer,  You order has been completed sucessfully."
							+ "  Please provide your feedback on this order ",
							"text/html");
					
					Transport.send(msg);
				}
			
	

	
}
