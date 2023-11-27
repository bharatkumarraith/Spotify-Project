package com.example.service;

import com.example.domain.UserDetails;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator{
    @Override
    public Map<String,String> generateToken(UserDetails userDetails) {

        //Here we are creating a token which is of a map type where key and value both will be string
        //In this map object we can store our token values which will be sent to client
        Map<String,String> tokenMap = new HashMap<String,String>();

        //Here we can store the userDetailsInfo
        Map<String,Object>  userDetailsInfo = new HashMap<>();
        userDetailsInfo.put("email",userDetails.getEmail());
        userDetailsInfo.put("user_name",userDetails.getUser_name());
        System.out.println("userDetails is in SecurityTokenGeneratorImpl ="+ userDetails.getUser_name());
        //In the below code we are creating a token string using Jwts object by setting the claim, issue date and signature

        String jwtTokenString = Jwts.builder().setClaims(userDetailsInfo).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS512,"myKey").compact();
        //tokenMap object Storing the jwtTokenString and a message //compact means to reduce the size
        tokenMap.put("token",jwtTokenString);
        tokenMap.put("Message","Login Successful");
        tokenMap.put("user_name",userDetails.getUser_name());
        return tokenMap;
    }
}
































































