package com.example.demo.service;

import com.example.demo.auth.LoginRequest;
import com.example.demo.auth.LoginResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public User createUser (User user){
        return userRepository.save(user);
    }

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(String id){
        return userRepository.findById(id).orElse(null);
    }

    public User findUserByEmailUcab( String email ){ return userRepository.findByEmailUcab(email).orElse(null);}
    @Transactional
    public User updateUser(String id, User user){
        User existingUser = userRepository.findById(id).orElse(null);
        if(existingUser != null){
            existingUser.setUserFirstName(user.getUserFirstName());
            existingUser.setUserLastName(user.getUserLastName());
            return userRepository.save(existingUser);
        }
        return null;
    }
    @Transactional
    public void deleteUserById(String id){
        userRepository.deleteById(id);
    }

    public Iterable<String> getUserRoles(String userDNI){
        return userRepository.getUserRoles(userDNI);
    }

    public Boolean validateUserAndPassword (String userDNI, String password){

        return userRepository.validateUserAndPassword(userDNI,password);
    }
}
