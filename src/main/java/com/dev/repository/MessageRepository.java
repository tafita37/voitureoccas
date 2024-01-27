package com.dev.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.dev.model.message.Message;
import com.dev.model.user.User;
import java.util.List;
import java.util.Date;


public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByUserSendAndUserReceiveOrUserReceiveAndUserSendOrderByDateHeureMessage(User userSend1, User userReceive1, User userReceive2, User userSend2 );
    public List<Message> findByUserSendAndUserReceive(User userSend, User userReceive);
    public List<Message> findByUserSend(User userSend);
}
