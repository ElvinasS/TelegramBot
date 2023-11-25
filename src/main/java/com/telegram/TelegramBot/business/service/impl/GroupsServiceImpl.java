package com.telegram.TelegramBot.business.service.impl;

import com.telegram.TelegramBot.business.mapper.GroupsMapStructMapper;
import com.telegram.TelegramBot.business.repository.GroupsRepository;
import com.telegram.TelegramBot.business.repository.model.GroupsDAO;
import com.telegram.TelegramBot.business.repository.model.UsersDAO;
import com.telegram.TelegramBot.business.service.GroupsService;
import com.telegram.TelegramBot.models.Groups;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class GroupsServiceImpl implements GroupsService {
    @Autowired
    GroupsRepository groupsRepository;
    @Autowired
    GroupsMapStructMapper groupsMapStructMapper;

    @Override
    public List<Groups> findAllGroups() {
        List<GroupsDAO> groupsDAOList = groupsRepository.findAll();
        log.info("Get training list. Size is: {}", groupsDAOList::size);
        return groupsDAOList.stream().map(groupsMapStructMapper::groupsDAOToGroups).collect(Collectors.toList());
    }
}
