package com.telegram.TelegramBot.business.repository;

import com.telegram.TelegramBot.business.repository.model.UsersDAO;
import com.telegram.TelegramBot.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersDAO, Long> {

    @Query(value = "SELECT * FROM known_users WHERE known_user_id=?",nativeQuery = true)
    public Users findByUserId(String userId);
}
