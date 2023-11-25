package com.telegram.TelegramBot;

//import com.telegram.TelegramBot.business.service.impl.MyBot;
import com.telegram.TelegramBot.controller.UsersController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication(scanBasePackages="com.telegram.TelegramBot")
@EnableAutoConfiguration
@EnableSpringConfigured
public class TelegramBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramBotApplication.class, args);

		UsersController myBot = new UsersController();
		try {
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			botsApi.registerBot(myBot);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
