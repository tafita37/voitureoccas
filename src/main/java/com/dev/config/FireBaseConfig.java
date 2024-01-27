package com.dev.config;


import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class FireBaseConfig {
    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource("notification-694c3-firebase-adminsdk-u2v35-f6f4963a2c.json").getInputStream());
        FirebaseOptions firebaseOptions = FirebaseOptions
                .builder()
                .setCredentials(googleCredentials)
                .build();
                FirebaseApp app=null;
        try {
            app=FirebaseApp.getInstance("my-app");
        } catch (IllegalStateException e) {
            app = FirebaseApp.initializeApp(firebaseOptions, "my-app");
        }
    return FirebaseMessaging.getInstance(app);
}

}