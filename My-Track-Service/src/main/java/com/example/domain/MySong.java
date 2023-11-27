package com.example.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class MySong {
    @Id

    private String songName;
    private String artistName;
    private String rating;


}
