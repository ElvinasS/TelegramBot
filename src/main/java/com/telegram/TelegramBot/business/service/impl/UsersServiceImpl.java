package com.telegram.TelegramBot.business.service.impl;

import com.telegram.TelegramBot.business.mapper.UsersMapStructMapper;
import com.telegram.TelegramBot.business.repository.UsersRepository;
import com.telegram.TelegramBot.business.repository.model.UsersDAO;
import com.telegram.TelegramBot.business.service.UsersService;
import com.telegram.TelegramBot.models.Users;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersRepository usersRepository;
    @Autowired
    UsersMapStructMapper usersMapStructMapper;

    @Override
    public List<Users> findAllUsers() {
        List<UsersDAO> usersDAOList = usersRepository.findAll();
        log.info("Get training list. Size is: {}", usersDAOList::size);
        return usersDAOList.stream().map(usersMapStructMapper::usersDAOToUsers).collect(Collectors.toList());
    }

    @Override
    public boolean findUserMatch(Users userId) {
        if (!hasMatch(userId)){
        return false;
        }
        else {
            return true;
        }
    }

    @Override
    public String findUserMatchString(String userId) {
        Users userID = new Users();
        userID.setUserId(userId);
        if (!hasMatch(userID)){
            return "false";
        }
        else {
            return "true";
        }
    }

    public boolean hasMatch(Users users){
        return usersRepository.findAll().stream().anyMatch(usersDAO -> isSame(users, usersDAO));
    }

    private boolean isSame(Users users, UsersDAO usersDAO) {
        return usersDAO.getUserId().equals(users.getUserId()) ;
    }
}
