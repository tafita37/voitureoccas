package com.dev.service.implementation;

import org.springframework.stereotype.Service;

import com.dev.model.Note;
import com.dev.service.FireBaseMessagingService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

@Service
public class FirebaseMessagingServiceImpl implements FireBaseMessagingService {

    private final FirebaseMessaging firebaseMessaging;

    public FirebaseMessagingServiceImpl(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }


    @Override
    public String sendNotification(Note note) throws FirebaseMessagingException {

        Notification notification = Notification
                .builder()
                .setTitle(note.getSubject())
                .setBody(note.getContent())
                .build();

        Message message = Message
                .builder()
                .setToken(note.getToken())
                .setNotification(notification)
                .putAllData(note.getData())
                .build();

        return firebaseMessaging.send(message);
    }

}
