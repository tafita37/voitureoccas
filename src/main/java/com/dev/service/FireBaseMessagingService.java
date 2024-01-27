package com.dev.service;

import com.dev.model.Note;
import com.google.firebase.messaging.FirebaseMessagingException;

/**
 * FireBaseMessagingService
 */
public interface FireBaseMessagingService {
    public String sendNotification(Note note) throws FirebaseMessagingException;
}