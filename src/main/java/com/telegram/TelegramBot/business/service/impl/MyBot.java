//package com.telegram.TelegramBot.business.service.impl;
//import com.telegram.TelegramBot.business.repository.UsersRepository;
//import com.telegram.TelegramBot.business.service.UsersService;
//import com.telegram.TelegramBot.models.Users;
//import jakarta.persistence.Entity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Service;
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
//import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
//@Configurable
//@Service
//public class MyBot extends TelegramLongPollingBot{
//
//    @Autowired
//    UsersService usersService;
//
////    @Autowired
////    UsersRepository usersRepository;
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            String messageText = update.getMessage().getText();
//            Long chatId = update.getMessage().getChatId();
//            // Process the message and generate a response
//            Users userID = new Users();
//            userID.setUserId(update.getMessage().getFrom().getId().toString());
//            System.out.println(update.toString() + usersService.findUserMatch(userID));
//            // Send the response back to the user
//
//            if(1 == 1) {
//                try {
//                        String response = processMessage(messageText, update);
//                        execute(new SendMessage(chatId.toString(), response));
//                    } catch (TelegramApiException e) {
//                        e.printStackTrace();
//                }
//            }
//            else {
//                System.out.println("invalid user " + update.getMessage().getFrom().getId().toString());
//            }
//        }
//    }
//
//    private String processMessage(String messageText, Update update) {
//        if(messageText.length()<2){
//            return "message too short";
//        }
//        else{
//            forwardMessageToGroups(messageText, update);
//            return "your message: " + messageText + " sent to groups";
//        }
//    }
//
//    private void forwardMessageToGroups(String message, Update update) {
//        // Replace "group1", "group2", etc. with the actual group chat IDs test
//        String[] groupChatIds = {
//                "-4072268298",
//                "-4092729878",
//                "-4034643420"
//        };
//
//        for (String chatId : groupChatIds) {
//            ForwardMessage forwardMessage = new ForwardMessage();
//            forwardMessage.setChatId(chatId);
//            forwardMessage.setFromChatId(update.getMessage().getChatId().toString());
//            forwardMessage.setMessageId(update.getMessage().getMessageId());
//            try {
//                execute(forwardMessage);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    String[] knownSenderIds = { "5297346890" , "5646456546" };
//
////    private void checkExistingUsers(String userId){
////        usersRepository.findAll().stream().anyMatch(usersDAO -> isSame(users, usersDAO));
////    }
//
//    @Override
//    public String getBotUsername() {
//        return "messagetestinator";
//    }
//    @Override
//    public String getBotToken() {
//        return "6817981477:AAHs_oaDLcKIkp5P-rB0OVOVrAJpXwoTcfg";
//    }
//
//
////    public String findUserMatch(Users userId) {
////        if (!hasMatch(userId)){
////            return "false";
////        }
////        else {
////            return "true";
////        }
////    }
////
////    public boolean hasMatch(Users users){
////        return usersRepository.findAll().stream().anyMatch(usersDAO -> isSame(users, usersDAO));
////    }
////
////    private boolean isSame(Users users, UsersDAO usersDAO) {
////        return usersDAO.getUserId().equals(users.getUserId()) ;
////    }
//}
