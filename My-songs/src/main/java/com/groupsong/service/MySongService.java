package com.groupsong.service;

import com.groupsong.domain.MySong;
import com.groupsong.repo.MySongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MySongService implements IMySongService {

    private MySongRepo mySongRepo;
    @Autowired
    public MySongService(MySongRepo mySongRepo){
        this.mySongRepo = mySongRepo;
    }
    @Override
    public List<MySong> getAllSongs() {
        return mySongRepo.findAll();
    }

    @Override
    public MySong addSong(MySong song) {
        return mySongRepo.save(song);
    }

    @Override
    public MySong getSongById(String songName) {
        return mySongRepo.findById(songName).get();
    }
}