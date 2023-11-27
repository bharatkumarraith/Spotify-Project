package com.example.service;

import com.example.domain.UserDetails;
import com.example.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserDetailsService {
    @Autowired
    UserDetailsRepo userDetailsRepo;
//    @Autowired
//    TrackRepo trackRepo;

//    @Autowired
//    public UserDetailsService(UserDetailsRepo userDetailsRepo,TracRepo tracRepo) {
//        this.userDetailsRepo = userDetailsRepo;
//        this.tracRepo = tracRepo;
//    }

    public UserDetails registerUser(UserDetails userDetails) {
        System.out.println("========= I am in register() Method - userDetails Service =========");
        System.out.println("User Detail Received From Client : " + userDetails);
//        trackRepo.save(track);
        return userDetailsRepo.save(userDetails);
    }

    public UserDetails checkAuthentication(UserDetails user) {
        System.out.println("i am in the UserDetailsServe layer");
        System.out.println("UserDetails are =="+ user);
        Optional<UserDetails> returnedUser1 = userDetailsRepo.findById(user.getEmail());


        if (returnedUser1.isPresent()) {
            UserDetails returnedUser = returnedUser1.get(); //getting the things for this method like username and password or emailId
            if (returnedUser.getEmail().equals(user.getEmail())){
                return returnedUser;
            }else {
                return null;
            }
        } else {
            return null;
        }
    }

//    public UserDetails RemoveUserInfo(int getUser_id){
//        Optional<UserDetails> returnUser = userDetailsRepo.findById(getUser_id);
//        if (returnUser.isEmpty()) {
//            return null;
//        } else {
//            UserDetails userDetails = returnUser.get();
//            userDetailsRepo.delete(userDetails);
//            return userDetails;
//        }
//    }
//
//    public UserDetails getUserInfo(int getUser_id){
//        if (userDetailsRepo.findById(getUser_id).isPresent()){
//            return userDetailsRepo.findById(getUser_id).get();
//        }else {
//            return null;
//        }
//    }
}
