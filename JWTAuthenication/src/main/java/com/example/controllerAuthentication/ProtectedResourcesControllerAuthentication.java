//package com.example.controllerAuthentication;
//
//import com.example.service.UserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/user2")
//public class ProtectedResourcesControllerAuthentication {
//
//    UserDetailsService userDetailsService;
//
//    @Autowired
//    public ProtectedResourcesControllerAuthentication (UserDetailsService userDetailsService){
//        this.userDetailsService=userDetailsService;
//    }
//    //http://localhost:8083/user2/userInfo/1
//    @GetMapping("/userInfo/{user_id}")
//    public ResponseEntity<?> getUserInfo(@PathVariable int user_id){
//        System.out.println("it is my user_id " + user_id);
//        if (userDetailsService.getUserInfo(user_id)!=null){
//            System.out.println(" it is my user_id "+ user_id);
//            return new ResponseEntity<>(userDetailsService.getUserInfo(user_id), HttpStatus.OK);
//        }else {
//            return new ResponseEntity<>("No Such User",HttpStatus.NOT_FOUND);
//        }
//    }
//    @GetMapping("/payment")
//    public String payment(){
//        return "payment Page";
//    }
//    //http:localhost:8080/api/v2/userinfo/user_id
//    @DeleteMapping("/userInfo/{user_id}")
//    public ResponseEntity<?> deleteUserInfo(@PathVariable int user_id){
//        System.out.println("its is my user_id" + user_id);
//        if (userDetailsService.RemoveUserInfo(user_id)!=null){
////            if(user_id = userDetailsService.RemoveUserInfo(user_id)!=null){
//            return new ResponseEntity<>(userDetailsService.getUserInfo(user_id), HttpStatus.OK);
//        }else {
//            return new ResponseEntity<>("No Such user Deleted ",HttpStatus.NOT_FOUND);
//        }
//    }
////    @PostMapping("/api/v2/use")
////    public ResponseEntity<?> generateUser(@RequestBody Track track){
////        UserDetails userDetails = new UserDetails();
////        userDetails.setUser_id(new Random(1000).nextInt());
////        userDetails.setUser_name("Raju");
////        userDetails.setUser_rating(track.getTrackRating());
////
////        UserDetails returnUserDetails = userDetailsService.registerUser(userDetails,track);
////
////        return new ResponseEntity<>(returnUserDetails,HttpStatus.OK);
////    }
//
//}
