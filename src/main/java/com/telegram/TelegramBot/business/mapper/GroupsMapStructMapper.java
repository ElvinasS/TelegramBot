package com.telegram.TelegramBot.business.mapper;

import com.telegram.TelegramBot.business.repository.model.GroupsDAO;
import com.telegram.TelegramBot.models.Groups;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface GroupsMapStructMapper {
    GroupsDAO groupsToGroupsDAO (Groups groups);
    Groups groupsDAOToGroups (GroupsDAO groupsDAO);

}
