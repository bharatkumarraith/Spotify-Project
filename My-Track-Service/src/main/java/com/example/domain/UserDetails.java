package com.example.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDetails {
    @Id
    private String email;
    private String password;
    private String user_name;
    private String address;
    List<PlaylistDetails> playlistDetails;



}


























