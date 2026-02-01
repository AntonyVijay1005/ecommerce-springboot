package com.project.ecommerce.Service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenService {

    private String secret = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlbWFpbEBnbWFpbC5jb20iLCJpYXQiOjE2";

    private SecretKey keys = Keys.hmacShaKeyFor(secret.getBytes());

    public String Generate_Jwt_Token(String email)
    {
        return Jwts.builder()
        .subject(email)
        .issuedAt(new Date())
        .expiration(new Date (System.currentTimeMillis()+1000*60))
        .signWith(keys)
        .compact();
    }

    public Claims Claim_Jwt_Token(String token)
    {
        return Jwts.parser().verifyWith(keys)
        .build().parseSignedClaims(token).getPayload();

    }

    public String Get_Email_From_Jwt(String token)
    {
        return Claim_Jwt_Token(token).getSubject();
    }

    public boolean Validate_Jwt_Token(String token,UserDetails userDetails)
    {
        String email = Get_Email_From_Jwt(token);
        if(email.equals(userDetails.getUsername()))
        {
            return true;
        }
        else{
            return false;
        }
    }




}
