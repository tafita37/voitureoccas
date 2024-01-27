package com.dev.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import com.dev.model.message.Message;
import com.dev.model.user.User;

public interface UserService {
    public Optional<User> findByEmail(String email);

    public Optional<User> findById(int id);

    public User loginUser(User user);

    public Message envoyerMessage(User userSend, User userReceive, String contenu, Timestamp dateTime) throws Exception;

    public List<Message> findByUserSendAndUserReceive(User userSend, User userReceive);

    public List<User> findDistinctUsersForUser(User user);
}
