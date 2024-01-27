package com.dev.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.config.JwtService;
import com.dev.model.message.Message;
import com.dev.model.user.User;
import com.dev.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping(path = "/envoyerMessage")
    public Message envoyerMessage(@RequestHeader Map<String, String> headers, @RequestParam int idReceive, @RequestParam String contenu) throws Exception {
        String email= jwtService.extractUserMail(headers.get("authorization").substring(7));
        User userSend=userService.findByEmail(email).get();
        User userReceive=userService.findById(idReceive).get();
        return userService.envoyerMessage(userSend, userReceive, contenu, Timestamp.valueOf(LocalDateTime.now()));
    }

    @GetMapping(path = "/allMessage")
    public List<Message> getAllMessage(@RequestHeader Map<String, String> headers, @RequestParam int idReceive) throws Exception {
        String email= jwtService.extractUserMail(headers.get("authorization").substring(7));
        User userSend=userService.findByEmail(email).get();
        User userReceive=userService.findById(idReceive).get();
        System.out.println("users : "+userSend+" "+userReceive);
        return userService.findByUserSendAndUserReceive(userSend, userReceive);
    }

    @GetMapping(path = "/allUserDiscuss")
    public List<User> getAllPersonneDiscuss(@RequestHeader Map<String, String> headers) {
        String email= jwtService.extractUserMail(headers.get("authorization").substring(7));
        User user=userService.findByEmail(email).get();
        System.out.println(user);
        return userService.findDistinctUsersForUser(user);
    }
}
