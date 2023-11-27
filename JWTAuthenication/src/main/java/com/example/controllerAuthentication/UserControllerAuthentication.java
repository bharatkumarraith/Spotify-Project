package com.example.controllerAuthentication;

import com.example.domain.UserDetails;
import com.example.domain.UserDetailsDTO;
import com.example.service.SecurityTokenGeneratorImpl;
import com.example.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserControllerAuthentication {

    UserDetailsService userDetailsService;

    @Autowired
    public UserControllerAuthentication(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    SecurityTokenGeneratorImpl securityTokenGenerator;
    //http://localhost:8083/api/v1/user

    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@RequestBody UserDetailsDTO userDetailsDTO) {
        System.out.println("========= I am in register() Method - userController =========");
        System.out.println("User Detail Received From Client : " + userDetailsDTO);
        UserDetails newUser = new UserDetails(userDetailsDTO.getEmail(),userDetailsDTO.getPassword(),
                userDetailsDTO.getUser_name(),userDetailsDTO.getAddress());
//        userDetailsService.registerUser();
        if (newUser != null) {
            return new ResponseEntity<>(userDetailsService.registerUser(newUser), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Error Occurred while Registered", HttpStatus.CREATED);
        }
    }

//    @PostMapping("/checkAuth")
//    public ResponseEntity<?> checkAuthentication(@RequestBody UserDetails userDetails){
//        UserDetails verifiedUser = userDetailsService.checkAuthentication(userDetails);
//        if (verifiedUser!=null){
//            return new ResponseEntity<>(verifiedUser,HttpStatus.OK);
//        }else {
//            return new ResponseEntity<>("Invalid Credential",HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/aboutUs")
    public String aboutUs() {
        return "AboutUs";
    }

    //http://localhost:8083/api/v1/checkAuth
    @PostMapping("/checkAuth")
    public ResponseEntity<?> checkAuthentication(@RequestBody UserDetails userDetails) {
        System.out.println("i am in the controller v2");
        UserDetails verifiedUser = userDetailsService.checkAuthentication(userDetails);
        if (verifiedUser != null) {
            return new ResponseEntity<>(securityTokenGenerator.generateToken(verifiedUser), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid Credential", HttpStatus.NOT_FOUND);
        }
    }


}



























