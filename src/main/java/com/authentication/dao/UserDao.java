package com.authentication.dao;

import com.authentication.model.MainUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<MainUser, Long> {
    MainUser findMainUserByUsername(String username);
}
