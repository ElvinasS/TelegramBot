package com.telegram.TelegramBot.business.repository;

import com.telegram.TelegramBot.business.repository.model.GroupsDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupsRepository extends JpaRepository<GroupsDAO, Long> {
}
