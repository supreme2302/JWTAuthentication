package com.authentication.service.impl;

import com.authentication.dao.RoleDao;
import com.authentication.dao.UserDao;
import com.authentication.model.MainUser;
import com.authentication.model.Role;
import com.authentication.model.UserDto;
import com.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MainUser user = userDao.findMainUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(MainUser user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            //authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
//			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
        });
        return authorities;
        //return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List<MainUser> findAll() {
        List<MainUser> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id) {
        userDao.deleteById(id);
    }

    @Override
    public MainUser findOne(String username) {
        return userDao.findMainUserByUsername(username);
    }

    @Override
    public MainUser findById(Long id) {
        return userDao.findById(id).get();
    }

    @Override
    public MainUser getUserByName(String name) {
        return userDao.findMainUserByUsername(name);
    }

    @Override
    public MainUser save(UserDto user) {
        MainUser newUser = new MainUser();
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        newUser.setUsername(user.getUsername());
        Role role = roleDao.findRoleByName("USER");
        if (role == null) {
            role = new Role();
            role.setName("USER");
            role.setDescription("TEST");
        }
        Set<Role> newSet = new HashSet<>();
        newSet.add(role);
        newUser.setRoles(newSet);
        return userDao.save(newUser);
    }
}
