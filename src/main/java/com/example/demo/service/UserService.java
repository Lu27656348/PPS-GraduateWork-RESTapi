package com.example.demo.service;

import com.example.demo.auth.LoginRequest;
import com.example.demo.auth.LoginResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Transactional
    public ResponseEntity<User> createUser (User user){
        User userSearch = userRepository.findById(user.getUserDNI()).orElse(null);
        if(userSearch == null){
            return ResponseEntity.ok(userRepository.save(user));
        }
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new User());
    }

    @Transactional
    public ResponseEntity<User> updateUser (User user){
        User userSearch = userRepository.findById(user.getUserDNI()).orElse(null);
        if(userSearch != null){
            userSearch.setUserEmail(user.getUserEmail());
            userSearch.setUserFirstName(user.getUserFirstName());
            userSearch.setUserLastName(user.getUserLastName());
            userSearch.setUserPhone(user.getUserPhone());
            return ResponseEntity.ok(userRepository.save(userSearch));
        }
        return (ResponseEntity<User>) ResponseEntity.badRequest();
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
