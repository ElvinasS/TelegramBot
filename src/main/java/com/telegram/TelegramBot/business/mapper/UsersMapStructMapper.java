package com.telegram.TelegramBot.business.mapper;

import com.telegram.TelegramBot.business.repository.model.UsersDAO;
import com.telegram.TelegramBot.models.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UsersMapStructMapper {
    UsersDAO usersToUsersDAO(Users users);
    Users usersDAOToUsers(UsersDAO usersDAO);
}
