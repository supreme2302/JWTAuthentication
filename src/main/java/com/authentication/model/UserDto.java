package com.authentication.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.beans.Transient;

public class UserDto {

    private String username;
    private String password;

    @JsonCreator
    public UserDto(
            @JsonProperty("password") String password,
            @JsonProperty("username") String username) {
        this.password = password;
        this.username = username;
    }

    @Transient
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
