package com.kodilla.carrental.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String name;
    private String lastName;
    private String email;
    private String password;
    private int phoneNumber;
    private Date accountCreated;
}
