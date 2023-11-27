package com.example.repo;

import com.example.domain.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackRepo extends MongoRepository<UserDetails,String> {

//    public PlaylistDetails findByPlaylistName(String playlistName);
//    public MySong  findByTrack(String trackName);
//    public  MySong findByTrackName(String trackName);

//    public UserDetails findBySongName(String trackName);
    @Query("{'playlistDetails.playlistName' : {$in : [?0]}}")
    List<UserDetails> findallUserFromPlaylistName(String PlaylistName);
}
