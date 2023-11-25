package com.telegram.TelegramBot.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@EqualsAndHashCode
@NoArgsConstructor
@Data
public class Groups {
    private Long id;
    private String groupId;
}
