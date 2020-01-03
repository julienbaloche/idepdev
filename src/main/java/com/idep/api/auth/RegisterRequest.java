package com.idep.api.auth;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequest {
    private String forename;
    private String surname;
    private String mail;
    private String password;
    private String username;}
