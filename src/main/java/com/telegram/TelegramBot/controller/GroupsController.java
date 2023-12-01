package com.telegram.TelegramBot.controller;

import com.telegram.TelegramBot.business.repository.GroupsRepository;
import com.telegram.TelegramBot.business.repository.UsersRepository;
import com.telegram.TelegramBot.business.service.GroupsService;
import com.telegram.TelegramBot.business.service.UsersService;
import com.telegram.TelegramBot.models.Groups;
import com.telegram.TelegramBot.models.Users;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v1/groups")
public class GroupsController {

    @Autowired
    GroupsService groupsService;

    @Autowired
    GroupsRepository groupsRepository;

    @GetMapping
    public ResponseEntity<List<Groups>> findAllGroups() {
        log.info("Retrieve list of group ids");
        List<Groups> groupsList = groupsService.findAllGroups();
        if (groupsList.isEmpty()) {
            log.warn("Group ids list is empty! {}", groupsList);
            return ResponseEntity.notFound().build();
        }
        log.debug("Group ids list is found. Size: {}", groupsList::size);
        return ResponseEntity.ok(groupsList);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Groups> saveGroups(@Valid @RequestBody Groups groups, BindingResult bindingResult) throws Exception {
        log.info("Save new group id by passing : {}", groups);
        if (bindingResult.hasErrors()) {
            log.error("New group id is not saved: error {}", bindingResult);
            return ResponseEntity.badRequest().build();
        }
        Groups groupSaved = groupsService.saveGroups(groups);
        log.debug("New group id is saved: {}", groups);
        return ResponseEntity.ok(groupSaved);
    }
}
