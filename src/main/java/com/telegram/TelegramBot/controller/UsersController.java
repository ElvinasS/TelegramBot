package com.telegram.TelegramBot.controller;

import com.telegram.TelegramBot.business.repository.UsersRepository;
import com.telegram.TelegramBot.business.repository.model.UsersDAO;
import com.telegram.TelegramBot.business.service.UsersService;
import com.telegram.TelegramBot.models.Users;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v1/users")
public class UsersController extends TelegramLongPollingBot {
    @Autowired
    UsersService usersService;

    @Autowired
    UsersRepository usersRepository;

    @GetMapping
    public ResponseEntity<List<Users>> findAllUsers() {
        log.info("Retrieve list of vehicle");
        Users userID = new Users();
        userID.setUserId("5297346890");

        log.warn(foundnot(userID));
        List<Users> usersList = usersService.findAllUsers();

        if (usersList.isEmpty()) {
            log.warn("Vehicle list is empty! {}", usersList);
            return ResponseEntity.notFound().build();
        }
        log.debug("Vehicle list is found. Size: {}", usersList::size);
        return ResponseEntity.ok(usersList);
    }

    public Boolean finduserMatch(Users userID){
        Boolean findUserMatch = usersService.findUserMatch(userID);
        return findUserMatch;
    }








    @GetMapping
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            // Process the message and generate a response
            Users userID = new Users();
            userID.setUserId(update.getMessage().getFrom().getId().toString());
            System.out.println(update.toString());
            // Send the response back to the user

            if(1==1) {
                try {
                    String response = processMessage(messageText, update);
                    execute(new SendMessage(chatId.toString(), response));
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("invalid user " + update.getMessage().getFrom().getId().toString());
            }
        }
    }

    private String foundnot(Users userID) {
        if(usersService.findUserMatch(userID) == true){
            return "message too st";
        }
        else{
            return "your message: ";
        }
    }


    private String processMessage(String messageText, Update update) {
        if(messageText.length()<2){
            return "message too short";
        }
        else{
            forwardMessageToGroups(messageText, update);
            return "your message: " + messageText + " sent to groups";
        }
    }

    private void forwardMessageToGroups(String message, Update update) {
        // Replace "group1", "group2", etc. with the actual group chat IDs test
        String[] groupChatIds = {
                "-4072268298",
                "-4092729878",
                "-4034643420"
        };

        for (String chatId : groupChatIds) {
            ForwardMessage forwardMessage = new ForwardMessage();
            forwardMessage.setChatId(chatId);
            forwardMessage.setFromChatId(update.getMessage().getChatId().toString());
            forwardMessage.setMessageId(update.getMessage().getMessageId());
            try {
                execute(forwardMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }






    @Override
    public String getBotUsername() {
        return "messagetestinator";
    }
    @Override
    public String getBotToken() {
        return "6817981477:AAHs_oaDLcKIkp5P-rB0OVOVrAJpXwoTcfg";
    }

}
