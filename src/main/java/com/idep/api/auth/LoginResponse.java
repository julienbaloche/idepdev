package com.idep.api.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse   implements Serializable {
    private final String jwttoken;

}
