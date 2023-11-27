package com.example.controller;


import com.example.domain.MySong;
import com.example.domain.PlaylistDetails;
import com.example.domain.UserDetails;
import com.example.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class TrackController {

    @Autowired
    TrackService trackService;

    //http://localhost:8088/api/v2/saveDetails
    @PostMapping("/saveDetails")
    public ResponseEntity<?> saveAllDetails(@RequestBody UserDetails userDetails) {
        UserDetails userDetails1 = trackService.saveDetails(userDetails);
        if (userDetails1 != null) {
            System.out.println("i am in saveDetails Method");
            return new ResponseEntity<>(userDetails1, HttpStatus.CREATED);
        } else
            return new ResponseEntity<>("Error is occur in saveMethod", HttpStatus.NOT_FOUND);
    }

    //http://localhost:8088/api/v2/getTrack
    @GetMapping("/getTrack")  //getting all the userDetails list
    public ResponseEntity<?> getAllTracks(HttpServletRequest httpServletRequest) {
        String email = (String) httpServletRequest.getAttribute("email");
        if (email != null) {
            System.out.println("i am in getTrack Method "  + trackService.getAllTracks(email));
            return new ResponseEntity<>(trackService.getAllTracks(email), HttpStatus.OK);
        } else
            return new ResponseEntity<>("Error is occur in GetTrack", HttpStatus.NOT_FOUND);
    }
    //http://localhost:8088/api/v2/deletePlaylist/

    //PathVariable:- it will connect to the endpoint to method parameter
    @DeleteMapping("/deletePlaylist/{playlist}")
    public ResponseEntity<?> removeUser(HttpServletRequest httpServletRequest,@PathVariable String playlist){
        String email = (String) httpServletRequest.getAttribute("email");
        if (email != null) {
            System.out.println("i am in deleteTrack Method" );
            return new ResponseEntity<>(trackService.deleteTrack(email,playlist),HttpStatus.OK);
        } else
            return new ResponseEntity<>("Error is occur in deletePlaylist/{playlist}", HttpStatus.NOT_FOUND);
    }
    //http://localhost:8088/api/v2/viewPlaylist
    @GetMapping("/viewPlaylist") // song in playlist (All PlaylistId & PlaylistName)
    public ResponseEntity<?> viewPlaylist(HttpServletRequest httpServletRequest ){
        String email = (String) httpServletRequest.getAttribute("email");
        if (email != null){
            System.out.println("it is in viewPlaylist");
            return new ResponseEntity<>(trackService.viewPlaylist(email),HttpStatus.OK);
        } else
            return new ResponseEntity<>("Error is occur in viewPlaylist", HttpStatus.NOT_FOUND);
    }

    //http://localhost:8088/api/v2/creatingPlaylist
    @PostMapping("/creatingPlaylist")  //creatingPlaylist
    public ResponseEntity<?> creatingPlaylist(HttpServletRequest httpServletRequest,@RequestBody PlaylistDetails playlistDetails){
        String email = (String) httpServletRequest.getAttribute("email");
        System.out.println("email is in track repo " + email);
        if (email != null){
            System.out.println("playlistDetails " + playlistDetails);
//            return new ResponseEntity<>(trackService.creatingPlaylist(email,playlistDetails),HttpStatus.CREATED);addToPlaylistTrack
            return new ResponseEntity<>(trackService.addToPlaylistTrack(email,playlistDetails),HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>("Error is occur in AddTrack", HttpStatus.NOT_FOUND);
    }
    //http://localhost:8088/api/v2/viewSongs
    @GetMapping("/viewSongs")
    public ResponseEntity<?> viewSong(){
        List<PlaylistDetails> listOfSongs = trackService.viewSongs();
        if (listOfSongs != null){
            return new ResponseEntity<>(listOfSongs,HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Error is occur in viewSongs",HttpStatus.NOT_FOUND);
        }
    }
    //http://localhost:8088/api/v2/get/songList
    @PostMapping("/get/songList")
    public ResponseEntity<?> getSongListByPlaylistName(HttpServletRequest request, @RequestParam String playlistName){
        String email = (String)request.getAttribute("email");
        System.out.println(email);
        System.out.println(playlistName);
        return new ResponseEntity(trackService.getSongsByPlaylistId(email,playlistName),HttpStatus.OK);
    }

    //http://localhost:8088/api/v2/check
    @GetMapping("/check")
    public String checking(){
        String play = trackService.check();
        return play;
    }

    //http://localhost:8088/api/v2/get/all-tracks
//    @GetMapping("/get/all-tracks")
//    public ResponseEntity<?> getTrackDetail(String playlistName){
//        return new ResponseEntity<>(trackService.getTrackDetails(playlistName),HttpStatus.OK);
//    }

//    @PostMapping("/addTrackside")  //creating playlist
//    public ResponseEntity<?> addTracks(HttpServletRequest httpServletRequest,@RequestBody PlaylistDetails playlistDetails){
//        String email = (String) httpServletRequest.getAttribute("email");
//        System.out.println("email is in track repo " + email);
//        if (email != null){
//            return new ResponseEntity<>(trackService.addTrack(email,playlistDetails),HttpStatus.CREATED);
//        }
//        else
//            return new ResponseEntity<>("Error is occur in AddTrack", HttpStatus.NOT_FOUND);
//    }
     //http://localhost:8088/api/v2/addSongTrack
//    @PostMapping("/addSongTrack")
//    public ResponseEntity<?> addSongs(String playlistName,@RequestBody String trackName ){
//        return new ResponseEntity<>(trackService.addSong(playlistName,trackName),HttpStatus.CREATED);
//    }

    // http://localhost:8088/api/v2/addPlaylist
    @PostMapping("/addPlaylist")
    public ResponseEntity<?> addPlaylist(HttpServletRequest httpServletRequest,@RequestBody PlaylistDetails playlist){
        String email =(String) httpServletRequest.getAttribute("email");
        System.out.println(email);
        return new ResponseEntity<>(trackService.addPlayList(email,playlist),HttpStatus.OK);
    }
    //http://localhost:8088/api/v2/add/play
    @PostMapping("/add/play")
    public ResponseEntity<?> addPlay(HttpServletRequest httpServletRequest,@RequestBody PlaylistDetails playlist,@RequestParam MySong trackDetails){
        String email = (String) httpServletRequest.getAttribute("email");
        System.out.println(email);
         boolean ss = trackService.addSongToPlaylist1(email,playlist,trackDetails);
         return new ResponseEntity<>(ss,HttpStatus.OK);

    }
    //http://localhost:8088/api/v2/addToPlay
    @PostMapping("/addToPlay")
    public ResponseEntity<?> addToPlayList11(HttpServletRequest httpServletRequest,@RequestBody PlaylistDetails playlistDetails){
        String email = (String) httpServletRequest.getAttribute("email");
        System.out.println(" it is my emailId = " + email);
        if (email!=null){
            System.out.println( "it is in controllerLayer PostMapping is done on addToPlay " );
            return new ResponseEntity<>(trackService.addToPlaylistTrack(email,playlistDetails),HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Error is on addToPlaylist11",HttpStatus.NOT_FOUND);
        }
    }


    //http://localhost:8088/api/v2/addSongToPlaylist11
//    @GetMapping("/addSongToPlaylist11/{playlistName}/{trackName}")
//    public ResponseEntity<?> addSongToPlaylist(HttpServletRequest httpServletRequest,@PathVariable String playlistName, @PathVariable String trackName){
////        String email = (String) httpServletRequest.getAttribute("email");
////        System.out.println(" it is my emailId = " + email );
////        if (email!=null){
////            System.out.println( "it is in controllerLayer" );
//            return new ResponseEntity<>(trackService.addSongToPlaylist( playlistName,trackName),HttpStatus.OK);
////        }else {
////            return new ResponseEntity<>("Error is on addSongToPlaylist",HttpStatus.NOT_FOUND);
////        }
//    }
}
























