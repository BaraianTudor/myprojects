package ro.fortech.demoapp.service;

import java.util.Date;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;

	private void send(MimeMessagePreparator preparator) {
		javaMailSender.send(preparator);
	}

	public void sendNewUser(String emailAddress) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

				message.setTo(emailAddress);
				message.setFrom(new InternetAddress("no-reply@VirtualMarketSpring.go.ro"));
				message.setSubject("Welcome to Virual Market!");
				message.setSentDate(new Date());
				message.setText("You can now login with your secret password!!");
			}
		};

		send(preparator);
	}
	
	public void revicedMessage(String toEmailAddress, String fromEmailAddress) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

				message.setTo(toEmailAddress);
				message.setFrom(new InternetAddress("no-reply@VirtualMarketSpring.go.ro"));
				message.setSubject("You got a new message on VirtualMarketSpring");
				message.setSentDate(new Date());
				message.setText("You have a new message from" + fromEmailAddress);
			}
		};

		send(preparator);
	}
	
	
	public void someoneJustViewedYourAdd(String emailAddress) {

		MimeMessagePreparator preparator = new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {

				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

				message.setTo(emailAddress);
				message.setFrom(new InternetAddress("no-reply@VirtualMarketSpring.go.ro"));
				message.setSubject("Good News from Virtual Market");
				message.setSentDate(new Date());
				message.setText("Hello " + emailAddress + ", we have good new for you, someone is just checking your add!");
			}
		};

		send(preparator);
	}
	
	

}
