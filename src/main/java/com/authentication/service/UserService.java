package com.authentication.service;

import com.authentication.model.MainUser;
import com.authentication.model.UserDto;

import java.util.List;

public interface UserService {

    MainUser save(UserDto user);

    List<MainUser> findAll();

    void delete(long id);

    MainUser findOne(String username);

    MainUser findById(Long id);

    MainUser getUserByName(String name);
}
