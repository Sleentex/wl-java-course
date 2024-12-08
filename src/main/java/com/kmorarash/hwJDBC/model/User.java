package com.kmorarash.hwJDBC.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private Long id;
    private String email;
    private String phoneNumber;
    private String password;

}
