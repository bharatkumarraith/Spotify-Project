package com.example.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
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

}
