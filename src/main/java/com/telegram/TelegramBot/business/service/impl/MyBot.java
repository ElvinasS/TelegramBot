package com.telegram.TelegramBot.business.service.impl;
import com.telegram.TelegramBot.models.Groups;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyBot extends TelegramLongPollingBot{

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            List<String> groupIdsList = getGroupsList();
            // Process the message and generate a response
//            Users userID = new Users();
//            userID.setUserId(update.getMessage().getFrom().getId().toString());
            System.out.println(update + getUserMatching(update.getMessage().getFrom().getId().toString()) + groupIdsList );

            if(groupIdsList.contains(update.getMessage().getChatId().toString())){
                System.out.println("Chat id in list");
            }
            else{
                System.out.println("Chat id not in list, adding to the list");
                if(getUserMatching(update.getMessage().getFrom().getId().toString()).equals("true")) {
                    saveNewGroupId(update.getMessage().getChatId().toString());
                }
                else{
                    System.out.println("user not authorized to add new group ids");
                }
            }
            // Send the response back to the user

            if(getUserMatching(update.getMessage().getFrom().getId().toString()).equals("true") ) {
                try {
                        String response = processMessage(messageText, update, groupIdsList);
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

    private String processMessage(String messageText, Update update, List<String> groupChatIds) {
        if(messageText.length()<2){
            return "message too short";
        }
        else{
            forwardMessageToGroups(messageText, update, groupChatIds);
            return "your message: " + messageText + " sent to groups";
        }
    }

    private void forwardMessageToGroups(String message, Update update, List<String> groupChatIds) {
        // Replace "group1", "group2", etc. with the actual group chat IDs test
//        String[] groupChatIds = {
//                "-4072268298",
//                "-4092729878",
//                "-4034643420"
//        };

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

//   String[] knownSenderIds = { "5297346890" , "5646456546" };

//    private void checkExistingUsers(String userId){
//        usersRepository.findAll().stream().anyMatch(usersDAO -> isSame(users, usersDAO));
//    }

    @Override
    public String getBotUsername() {
        return "messagetestinator";
    }
    @Override
    public String getBotToken() {
        return "XXXXXXXX";
    }

    private static String getUserMatching(String userId)
    {
        final String uri = "http://localhost:8080/api/v1/users/getUserMatch/" + userId;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        return result;
    }

    private static List<String> getGroupsList() {

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Groups[]> response =
                restTemplate.getForEntity(
                        "http://localhost:8080/api/v1/groups",
                        Groups[].class);
        Groups[] groupIdsList = response.getBody();

        List<String> groupList = new ArrayList<>();

        for (Groups groups : groupIdsList) {
            groupList.add(groups.getGroupId());
        }

        return  groupList;
    }

    private static void saveNewGroupId(String groupId){
        RestTemplate restTemplate = new RestTemplate();
        Groups newGroupId = new Groups();
        newGroupId.setGroupId(groupId);
        newGroupId.setId(null);
        restTemplate.postForObject(
                "http://localhost:8080/api/v1/groups",
                newGroupId,
                Groups.class);
    }
}
