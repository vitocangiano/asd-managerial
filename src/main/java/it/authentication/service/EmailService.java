package it.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	JavaMailSender javaMailSender;

	public void sendMail(String toEmail, String subject, String message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setTo(toEmail);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);

		mailMessage.setFrom("");

		javaMailSender.send(mailMessage);
	}

}
