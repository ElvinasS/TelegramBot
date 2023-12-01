package com.telegram.TelegramBot.controller;

import com.telegram.TelegramBot.business.repository.UsersRepository;
import com.telegram.TelegramBot.business.service.UsersService;
import com.telegram.TelegramBot.models.Users;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v1/users")
public class UsersController {
    @Autowired
    UsersService usersService;

    @Autowired
    UsersRepository usersRepository;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping
    public ResponseEntity<List<Users>> findAllUsers() {
        log.info("Retrieve list of users");
        List<Users> usersList = usersService.findAllUsers();
        if (usersList.isEmpty()) {
            log.warn("User list is empty! {}", usersList);
            return ResponseEntity.notFound().build();
        }
        log.debug("User list is found. Size: {}", usersList::size);
        return ResponseEntity.ok(usersList);
    }

    @GetMapping(value = "/getUserMatch/{userId}")
    public @ResponseBody String findUserMatchStrings(@NonNull @PathVariable String userId) {
        String url = "http://localhost:8080/api/v1/users";
        Users userID = new Users();
        userID.setUserId(userId);
        String result = usersService.findUserMatchString(userID.getUserId().toString());
        return result;
    }
////    @GetMapping(value = "/getUserMatch/{userId}")
//        public String findUserMatchString(String userId) {
//        RestTemplate restTemplate = new RestTemplate();
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//            HttpEntity<String> entity = new HttpEntity<String>(headers);
//
//            return restTemplate.exchange("http://localhost:8080/api/v1/users/getUserMatch/5297346890", HttpMethod.GET, entity, String.class).getBody();
//
//    }
//
//    public Boolean finduserMatch(Users userID){
//        Boolean findUserMatch = usersService.findUserMatch(userID);
//        return findUserMatch;
//    }







//
//    @Override
//    public void onUpdateReceived(Update update) {
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            String messageText = update.getMessage().getText();
//            Long chatId = update.getMessage().getChatId();
//            // Process the message and generate a response
////            String result = usersRepository.findByUserId(chatId.toString()).getUserId();
//            System.out.println(update.toString() + findUserMatchString(update.getMessage().getChatId().toString()));
//            // Send the response back to the user
//
//            if(1==1) {
//                try {
//                    String response = processMessage(messageText, update);
//                    execute(new SendMessage(chatId.toString(), response));
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            }
//            else {
//                System.out.println("invalid user " + update.getMessage().getFrom().getId().toString());
//            }
//        }
//    }
//
//    private String foundnot(Update update) {
//        Users userID = new Users();
//        userID.setUserId(update.getMessage().getFrom().getId().toString());
//        if(usersService.findUserMatch(userID) == true){
//            return "message too st";
//        }
//        else{
//            return "your message: ";
//        }
//    }
//
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






//    @Override
//    public String getBotUsername() {
//        return "messagetestinator";
//    }
//    @Override
//    public String getBotToken() {
//        return "6817981477:AAHs_oaDLcKIkp5P-rB0OVOVrAJpXwoTcfg";
//    }

}
