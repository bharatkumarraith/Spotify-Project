package com.example.proxy;

import com.example.domain.UserDetails;
import com.example.domain.UserDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "JWTAuthentication",url = "http://localhost:8083")
public interface JwtProxy {
    @PostMapping("/api/v1/user")
    public ResponseEntity<UserDetails> saveDetails(@RequestBody UserDetailsDTO userDetailsDTO);
}
