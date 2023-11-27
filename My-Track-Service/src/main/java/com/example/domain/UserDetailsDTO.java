package com.example.domain;


import lombok.*;

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
