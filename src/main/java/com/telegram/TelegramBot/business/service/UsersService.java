package com.telegram.TelegramBot.business.service;

import com.telegram.TelegramBot.models.Users;

import java.util.List;

public interface UsersService {
    List<Users> findAllUsers();

    boolean findUserMatch(Users userId);

    public String findUserMatchString(String userId);
}
