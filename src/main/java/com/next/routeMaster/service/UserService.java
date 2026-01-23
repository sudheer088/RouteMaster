package com.next.routeMaster.service;

import com.next.routeMaster.entity.User;
import com.next.routeMaster.repository.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    public UserService(UserRepo userRepo,PasswordEncoder passwordEncoder){
        this.userRepo=userRepo;
        this.passwordEncoder=passwordEncoder;
    }
    public User register (User user){
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       return userRepo.save(user);
    }
    public User login(String username,String password){
        User user= userRepo.findByUsername(username).orElseThrow(()->new RuntimeException("invalid credentials"));
        if(passwordEncoder.matches(password,user.getPassword())){
            return user;
        }
        throw new RuntimeException("invalid password");
    }
}
