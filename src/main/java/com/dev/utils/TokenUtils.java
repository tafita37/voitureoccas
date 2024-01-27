package com.dev.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;

import java.util.Date;

import org.springframework.stereotype.Component;


@Component
public class TokenUtils {
    
    private static String secret = "test";

    private String generateToken(int idUser){
        Date issuedAt = new Date(); 

        // claims 
        Claims claims = Jwts.claims().setIssuer(String.valueOf(idUser)).setIssuedAt(issuedAt);

        // generate JWT 
        return Jwts.builder().setClaims(claims).compact();
    }
}
