package ro.fortech.demoapp.configuration;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

	@Bean
	public JavaMailSender mailSender() {

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(465);
		mailSender.setProtocol("smtps");
		mailSender.setUsername("virtualmarketspring@gmail.com");
		mailSender.setPassword("0327345601");

		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", true);
		mailProperties.put("mail.smtp.starttls.enable", true);
		mailProperties.put("mail.smtp.starttls.required", true);
		mailProperties.put("mail.smtp.socketFactory.port", 465);
		mailProperties.put("mail.smtp.debug", true);
		mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		mailProperties.put("mail.smtp.socketFactory.fallback", false);

		return mailSender;
	}

}
