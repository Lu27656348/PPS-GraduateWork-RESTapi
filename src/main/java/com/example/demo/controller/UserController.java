package com.example.demo.controller;

import com.example.demo.auth.LoginRequest;
import com.example.demo.auth.LoginResponse;
import com.example.demo.auth.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("create")
    public ResponseEntity<User> createUser(@RequestBody User user){
        System.out.println(user);
        return userService.createUser(user);
    }

    @PutMapping("put")
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @GetMapping
    public Iterable<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("email/{email}")
    public User getUserByEmailUcab(@PathVariable String email){
        return userService.findUserByEmailUcab(email);
    }
    @GetMapping("{id}")
    public User getUserById(@PathVariable String id){
        return userService.getUserById(id);
    }

    @PutMapping("{id}")
    public User updateUser(@PathVariable String id, @RequestBody User user){
        return userService.updateUser(id,user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable String id){
        userService.deleteUserById(id);
    }

    @GetMapping("roles/{dni}")
    public Iterable<String> getUserRoles(@PathVariable String dni){
        return userService.getUserRoles(dni);
    }

    @PostMapping("validate")
    public Boolean validateUserAndPassword(@RequestBody LoginRequest loginRequest){
        return userService.validateUserAndPassword(loginRequest.getUserDNI(),loginRequest.getPassword());
    }
    @PostMapping("registration")
    public ResponseEntity<User> registerUser(@RequestBody RegisterRequest registerRequest){
        User newUser = new User(registerRequest.getUserDNI(),registerRequest.getPassword(),registerRequest.getUserfirstname(),registerRequest.getUserlastname(),registerRequest.getUseremailucab());
        return userService.createUser(newUser);
    }
}
