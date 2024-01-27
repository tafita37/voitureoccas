package com.dev.controller;

import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.model.user.User;
import com.dev.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/main")
public class IndexController {

    @Autowired 
    private UserService userService;
    
    @GetMapping(path = "/hello" , produces = "application/json")
    public String getHello(){
        return "Hello test!!";
    }

    @PostMapping(path = "/logUser" , consumes = "application/json" , produces = "application/json")
    public Hashtable<String, Object> loginUser(@RequestBody User user){
        Hashtable<String, Object> response = new Hashtable<>();
        User tmpuser = userService.loginUser(user);

        if(tmpuser == null){
            response.put("Status" , "failed");
            response.put("Message" , "Introuvable " + user.getMail());
            return response;
        }
            response.put("Status" , "oke");
            response.put("Message" , "Réussi ");
            response.put("Data" , tmpuser);
            return response;
    }

    @PostMapping(path = "/test" , produces = "application/json")
    public String test(){
        return "Hello EEEE!!";
    }
}
