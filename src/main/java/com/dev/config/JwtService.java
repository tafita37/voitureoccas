package com.dev.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final static String secret_key = "8109fdc8316c72a157d1bf2bae5c6f62f5ae178062d4cb42e165c0bf2cfc7897";

    public String extractUserMail(String jwtToken) {
        return extractClaims(jwtToken, Claims::getSubject);
    }

    public <T> T extractClaims(String jwtToken , Function<Claims, T> claimsResolver){
        final Claims claimsObjet = extractAllClaims(jwtToken);
        return claimsResolver.apply(claimsObjet);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>() , userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
            .signWith(getSignIngKey() , SignatureAlgorithm.HS256)
            .compact();
    }

    // Pour valider un token 
    public boolean isTokenValid(String jwtToken , UserDetails userDetails){
        final String username = extractUserMail(jwtToken);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(jwtToken);
    }

    private boolean isTokenExpired(String jwtToken) {
        return extractExpiration(jwtToken).before(new Date());
    }

    private Date extractExpiration(String jwtToken) {
        return extractClaims(jwtToken, Claims::getExpiration);
    }

    private Claims extractAllClaims(String jwtToken){
        return Jwts
        .parserBuilder()
        // pour créer la signature 
        .setSigningKey(getSignIngKey())
        .build()
        .parseClaimsJws(jwtToken)
        .getBody();
    }

    private Key getSignIngKey() {
        byte[] keyByte = Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(keyByte);
    }

}
