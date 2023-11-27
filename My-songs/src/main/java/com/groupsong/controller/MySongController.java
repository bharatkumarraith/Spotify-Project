package com.groupsong.controller;

import com.groupsong.domain.MySong;
import com.groupsong.service.IMySongService;
import com.groupsong.service.MySongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v4")
public class MySongController {
    private IMySongService iMySongService;
    @Autowired
    public MySongController(IMySongService iMySongService){
        this.iMySongService = iMySongService;
    }
    //http://localhost:6262/api/v4/get/all-songs
    @GetMapping("/get/all-songs")
    public ResponseEntity<?> getAllSongs(){
        return new ResponseEntity<>(iMySongService.getAllSongs(), HttpStatus.OK);
    }
    // http://localhost:6262/api/v4/addSong
    @PostMapping("/addSong")
    public ResponseEntity<?> addSong(@RequestBody MySong song){
        return new ResponseEntity<>(iMySongService.addSong(song),HttpStatus.CREATED);
    }

    //http://localhost:6262/api/v4/songBy/{id}
    @GetMapping("/songBy/{id}")
    public ResponseEntity<?> getSongById(@PathVariable String id){
        return new ResponseEntity<>(iMySongService.getSongById(id),HttpStatus.OK);
    }
}
