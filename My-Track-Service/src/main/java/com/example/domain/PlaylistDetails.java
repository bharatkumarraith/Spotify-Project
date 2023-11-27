package com.example.domain;


import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class PlaylistDetails {

    @Id
    private String playlistName;

    List<MySong> trackDetails;


}

