package com.example.domain;

import lombok.*;

import javax.persistence.Entity;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDetailsDTO {

    private String email;
    private String password;
    private String user_name;
    private String address;
}
