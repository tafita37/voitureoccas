package com.dev.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.config.JwtService;
import com.dev.model.Note;
import com.dev.model.user.User;
import com.dev.service.FireBaseMessagingService;
import com.dev.service.UserService;
import com.google.firebase.messaging.FirebaseMessagingException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final FireBaseMessagingService firebaseService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> regist(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(authenticationService.register(request));
        
    }

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/notification")
    public String sendNotification(@RequestBody Note note) {
        try {
            return firebaseService.sendNotification(note);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @GetMapping("/findUserById")
    public User findUserById(@RequestParam int idUser) {
        return userService.findById(idUser).get();
    }
}
