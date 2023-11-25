package com.telegram.TelegramBot.business.service;

import com.telegram.TelegramBot.models.Groups;

import java.util.List;

public interface GroupsService {
    List<Groups> findAllGroups();
}
