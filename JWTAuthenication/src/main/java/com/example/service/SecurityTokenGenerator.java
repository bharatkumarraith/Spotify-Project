package com.example.service;

import com.example.domain.UserDetails;

import java.util.Map;

public interface SecurityTokenGenerator {
    public Map<String,String> generateToken(UserDetails userDetails);

}
