package com.example.service;

import com.example.domain.PlaylistDetails;
import com.example.domain.MySong;
import com.example.domain.UserDetails;
import com.example.domain.UserDetailsDTO;
import com.example.proxy.JwtProxy;
//import com.example.repo.PlaylistRepo;
import com.example.repo.TrackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrackService {
    @Autowired
    TrackRepo trackRepo;
//    PlaylistRepo playlistRepo;

    @Autowired
    JwtProxy jwtProxy;
    public UserDetails saveDetails(UserDetails userDetails){
        List<PlaylistDetails> playlistDetails = new ArrayList<>();
        userDetails.setPlaylistDetails(playlistDetails);
        ResponseEntity<?> details = jwtProxy.saveDetails(new UserDetailsDTO( userDetails.getEmail(),
                userDetails.getPassword(), userDetails.getUser_name(), userDetails.getAddress()));
        System.out.println(details.getBody());
        return trackRepo.save(userDetails);

    }

//    public UserDetails addTrack(int user_id,PlaylistDetails playlistDetails){
//        UserDetails userDetails11 = trackRepo.findById(user_id).get();
//        if (userDetails11!= null){
//            List<PlaylistDetails> tracks = userDetails11.getPlaylistDetails(); //getTrack();
//            for (PlaylistDetails p : tracks){
//                if (p.getPlaylistName().equals(playlistDetails.getPlaylistName()))
//            }
//            tracks.add(playlistDetails);
//            userDetails11.setUserDetails(tracks);
//            return trackRepo.save(userDetails11);
//        }
//        else
//            return null;
//    }


    public UserDetails creatingPlaylist(String email,PlaylistDetails playlistDetails ){
        System.out.println("email " + email);
        Optional <UserDetails>  userDetails11= trackRepo.findById(email);
        System.out.println("userDetails11 143 456 " + userDetails11);
        System.out.println("email " + email);
        if (userDetails11.isPresent()){
            System.out.println("userDetails11  143 " + userDetails11.get());
            UserDetails playlistDetails1 = userDetails11.get();
            playlistDetails1.getPlaylistDetails().add(playlistDetails);
//            return trackRepo.save(userDetails11);
            return trackRepo.save(playlistDetails1);
        }
        else
            return null;
    }

    public UserDetails deleteTrack(String email,String playlistName){
        Optional<UserDetails> userDetails12 = trackRepo.findById(email);
       if (userDetails12.isPresent()){
           UserDetails userDetails = userDetails12.get();
           System.out.println("it is a deleteTrack");
           List<PlaylistDetails> playlist = userDetails.getPlaylistDetails();
           if (playlist  != null){
               PlaylistDetails removePlayList = playlist.stream().
                       filter(list -> playlistName.equals(list.getPlaylistName())).findFirst().orElse(null);
               playlist.remove(removePlayList);
               System.out.println("it ia second delete if method ( )");
               userDetails.setPlaylistDetails(playlist);
               return trackRepo.save(userDetails);
           }
           else {
               return null;
           }
       }
       else{
           return null;
       }
    }

//    public Track removeTrack(int trackId) throws TrackFoundEmptyException {
//        Optional<Track> track1 = trackRepo.findById(trackId);
//        if (track1.isEmpty()) {
//            throw new TrackFoundEmptyException("Track Found Empty");
//
//        } else {
//            Track track2 = track1.get();
//            trackRepo.delete(track2);
//            return track2;
//        }
//    }


    public List<PlaylistDetails> viewPlaylist(String email){
        UserDetails details = trackRepo.findById(email).get();
        if (details != null){
            List<PlaylistDetails> playlistDetails = details.getPlaylistDetails();
            return playlistDetails;
        }
        else {
            return null;
        }
    }

    public List<PlaylistDetails> viewSongs(){
        String admin = "Admin994@gmail.com";
        UserDetails details = trackRepo.findById(admin).get();
        if (details!=null){
            List<PlaylistDetails> listOfSong = details.getPlaylistDetails();
            return listOfSong;
        }else {
            return null;
        }
    }
    public List<UserDetails> getAllTracks(String email){
        UserDetails userDetails13 = trackRepo.findById(email).get();
        if (userDetails13!= null){
            List<UserDetails> TrackList = trackRepo.findAll();
            return TrackList;
        }
        else
            return null;
    }


    public String check(){
        return "It is a Spotify";
    }

//    public List<MySong> getTrackDetails(){
//        return playlistRepo.findAll();
//    }

//    public PlaylistDetails addSong(String playlistName, MySong trackDetails) {
//        System.out.println("playlistName " + playlistName);
//        Optional <PlaylistDetails> playlistDetails = playlistRepo.findById(playlistName);
//        System.out.println("playlistDetails " + playlistDetails);
//        if (playlistDetails.isPresent()){
//            PlaylistDetails playlistDetails1 = playlistDetails.get();
//            playlistDetails1.getTrackDetails().add(trackDetails);
//            return playlistRepo.save(playlistDetails1);
//        }  else{
//            return null;
//        }
//
//    }


//    public PlaylistDetails addSong(String playlistName, String trackName) {
//        if (playlistRepo.findByPlaylistName(playlistName) != null){
//            PlaylistDetails playlistDetails = playlistRepo.findByPlaylistName(playlistName);
//           MySong track = new MySong(trackName);
//           List<MySong> tracklist = playlistDetails.getTrackDetails();
//           tracklist.add(track);
//           playlistDetails.setTrackDetails(tracklist);
//           return playlistRepo.save(playlistDetails);
//        }else{
//            return null;
//        }
//
//    }

//public PlaylistDetails addSong(String playlistName, String songName) {
//    PlaylistDetails playlistDetails = playlistRepo.findByPlaylistName(playlistName);
//
//    if (playlistDetails == null) {
//        // Create a new playlist if it doesn't exist
//        playlistDetails = new PlaylistDetails();
//        playlistDetails.setPlaylistName(playlistName);
//        // You might want to set other properties of the playlist here as well
//    }
//
//    // Create a new track and add it to the playlist
//    MySong track = new MySong(songName);
//    List<MySong> tracklist = playlistDetails.getTrackDetails();
//    List<PlaylistDetails> list = new ArrayList<>();
//
//    tracklist.add(track);
//    playlistDetails.setTrackDetails(tracklist);
//    System.out.println("playlistName " + playlistDetails);
//
//    // Save the playlist (whether it's a new one or an existing one)
//    return playlistRepo.save(playlistDetails);
//}
/*
public boolean addTrackToPlaylist(String playlistName, String trackName) {
    // Find the playlist by name
    PlaylistDetails playlist = playlistRepo.findByPlaylistName(playlistName);
    if (playlist == null) {
        // Playlist not found, handle the error or return false
        return false;
    }

    // Find the track by name
    MySong track = trackRepo.findById(trackName).orElse(null);
    if (track == null) {
        // Track not found, handle the error or return false
        return false;
    }

    // Add the track to the playlist's trackDetails list
    playlist.getTrackDetails().add(track);

    // Save the updated playlist
    playlistRepo.save(playlist);

    return true;
}*/
//public PlaylistDetails addSongToPlaylist(String playlistName, String trackName,String email) {
//        Optional<UserDetails> userDetails = trackRepo.findById(email);
//        List<PlaylistDetails> userPlaylist = userDetails.get().getPlaylistDetails();
//        if (userPlaylist!= null){
//            Optional<PlaylistDetails> playlistDetails = userPlaylist.stream().filter(data -> data.getPlaylistName().equalsIgnoreCase(playlistName))
//        }
//                return null;
//}

//    public List<MySong> getTrackDetails(String playlistName){
//        PlaylistDetails playlistDetails = playlistRepo.findById(playlistName).get();
//        if (playlistDetails != null){
//            List<MySong> trackDetails = playlistDetails.getTrackDetails();
//            return trackDetails;
//        }else {
//            return null;
//        }
//    }


    public UserDetails addPlayList(String email, PlaylistDetails playlist) {
        UserDetails user = trackRepo.findById(email).get();
        // to check whether this playlist exists or not.
        boolean playlistExists = false;
        if (user.getPlaylistDetails() != null) {
            for (PlaylistDetails list : user.getPlaylistDetails()) {
                if (list.getPlaylistName().equals(playlist.getPlaylistName())) {

                    list.getTrackDetails().add(playlist.getTrackDetails().get(0));
                    playlistExists = true;
                    break;
                }
            }
        }
        if (!playlistExists) {
            if (user.getPlaylistDetails() == null) {
                user.setPlaylistDetails(new ArrayList<>());
            }
            user.getPlaylistDetails().add(playlist);
        }
        trackRepo.save(user);
        return user;
    }

    public boolean addSongToPlaylist1(String email,PlaylistDetails playlistDetails, MySong trackToAdd) {
        UserDetails user = trackRepo.findById(email).orElse(null);
        if (user == null) {
            return false; // User not found
        }


        PlaylistDetails playlist = user.getPlaylistDetails().get(0);
        if (playlist == null) {
            return false; // Playlist not found
        }

        playlist.setTrackDetails((List<MySong>) trackToAdd);
        trackRepo.save(user);
        return true;
    }
//    public UserDetails addtoplay11(String email,PlaylistDetails playlistDetails){
//        UserDetails details = trackRepo.findById(email).get();
//        System.out.println(details);
//        if (details != null){
//            List<PlaylistDetails> playlistDetails1 = details.getPlaylistDetails();
//            playlistDetails1.add(playlistDetails);
//            details.setPlaylistDetails(playlistDetails1);
//            System.out.println(playlistDetails1);
//            return trackRepo.save(details);
//        }else
//            return null;
//    }

    public UserDetails addToPlaylistTrack(String email,PlaylistDetails playlistDetails){
        UserDetails user = trackRepo.findById(email).get();
        boolean playlistExist = false;
        System.out.println("it is the playlist Details " + playlistDetails);
        if (user.getPlaylistDetails() !=null){
            for (PlaylistDetails list : user.getPlaylistDetails()){
                if (list.getPlaylistName().equals(playlistDetails.getPlaylistName())){
                    list.getTrackDetails().add(playlistDetails.getTrackDetails().get(0));
                    System.out.println("Having the same playlist " );
                    playlistExist = true;
                    break;
                }
            }
        }
        if (!playlistExist) {
            if (user.getPlaylistDetails()==null){
                user.setPlaylistDetails(new ArrayList<>());
            }
            user.getPlaylistDetails().add(playlistDetails);
        } trackRepo.save(user);
        return user;
    }

//    public PlaylistDetails addSongToPlaylist(String playlistName,String trackName)  {
//        System.out.println("This is addSongPlaylist-Service");
//        if(playlistRepo.findByPlaylistName(playlistName) != null){
//            PlaylistDetails playlist = playlistRepo.findByPlaylistName(playlistName);
//            MySong song  = (MySong) trackRepo.findallUserFromPlaylistName(trackName);
//            System.out.println(playlist);
//            System.out.println(song);
//            List<MySong> list = playlist.getTrackDetails();
//            list.add(song);
//            playlist.setTrackDetails(list);
//            return playlistRepo.save(playlist);
//        }
//        else {
//            //throw new PlaylistNotFoundException();
//            return null;
//        }
//    }

    public List<MySong> getSongsByPlaylistId(String email, String playlistName) {
        System.out.println(email);
        List<PlaylistDetails> userPlaylist = trackRepo.findById(email).get().getPlaylistDetails();
        if(userPlaylist!=null) {
            for (PlaylistDetails playlist : userPlaylist) {
                if (playlist.getPlaylistName().equals(playlistName)) {
                    return playlist.getTrackDetails();
                }
            }
        }
        return null;
    }
}































