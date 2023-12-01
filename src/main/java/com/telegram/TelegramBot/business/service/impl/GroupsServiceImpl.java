package com.telegram.TelegramBot.business.service.impl;

import com.telegram.TelegramBot.business.mapper.GroupsMapStructMapper;
import com.telegram.TelegramBot.business.repository.GroupsRepository;
import com.telegram.TelegramBot.business.repository.model.GroupsDAO;
import com.telegram.TelegramBot.business.repository.model.UsersDAO;
import com.telegram.TelegramBot.business.service.GroupsService;
import com.telegram.TelegramBot.models.Groups;
import com.telegram.TelegramBot.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

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
        log.info("Get group list. Size is: {}", groupsDAOList::size);
        return groupsDAOList.stream().map(groupsMapStructMapper::groupsDAOToGroups).collect(Collectors.toList());
    }

    @Override
    public Groups saveGroups(Groups groups) throws Exception {
        if (!hasMatch(groups)) {
            GroupsDAO groupsSaved = groupsRepository.save(groupsMapStructMapper.groupsToGroupsDAO(groups));
            log.info("New group id saved: {}", () -> groupsSaved);
            return groupsMapStructMapper.groupsDAOToGroups(groupsSaved);
        }else {
            log.error("Group id conflict exception is thrown: {}", HttpStatus.CONFLICT);
            throw new HttpClientErrorException(HttpStatus.CONFLICT);
        }
    }

    public boolean hasMatch(Groups groups){
        return groupsRepository.findAll().stream().anyMatch(groupsDAO -> isSame(groups, groupsDAO));
    }

    private boolean isSame(Groups groups, GroupsDAO groupsDAO) {
        return groupsDAO.getGroupId().equals(groups.getGroupId()) ;
    }
}
