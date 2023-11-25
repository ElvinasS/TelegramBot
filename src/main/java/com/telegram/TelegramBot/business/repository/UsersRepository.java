package com.telegram.TelegramBot.business.repository;

import com.telegram.TelegramBot.business.repository.model.UsersDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersDAO, Long> {
}
