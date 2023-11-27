package com.groupsong.service;

import com.groupsong.domain.MySong;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMySongService {
    public List<MySong> getAllSongs();
    public MySong addSong(MySong song);
    public MySong getSongById(String songName);
}
