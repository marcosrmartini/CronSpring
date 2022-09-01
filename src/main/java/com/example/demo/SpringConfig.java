package com.example.demo;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class SpringConfig {

	Logger logger = LoggerFactory.getLogger(SpringConfig.class);
	
	@Autowired
	private JavaMailSender mailSender;

//	
//	*   *   *   *   *       
//	│   │   │   │   │
//	│   │   │   │   └────── em quais dias da semana de 0 a 7 (tanto 0 quanto 7 são Domingo)
//	│   │   │   └────────── em quais meses    (1 - 12)
//	│   │   └────────────── em quais dias     (1 - 31)
//	│   └────────────────── em quais horas    (0 - 23)
//	└────────────────────── em quais minutos  (0 - 59)
//	
	// @Scheduled(cron = "*/5 5-20 * * * ?")
	@Scheduled(fixedDelay = 5, timeUnit = TimeUnit.MINUTES)
	@Async
	public void scheduleFixedDelayTask() {
		logger.info("Fixed delay task 0 - " + System.currentTimeMillis() / 1000);
	}

	public String sendMail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setText("Hello from Spring Boot Application");
		message.setTo("marcos.martini@gmail.com");
		message.setFrom("tems.br@gmail.com");

		try {
			mailSender.send(message);
			return "Email enviado com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao enviar email.";
		}
	}

}
