package com.rentalapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rentalapp.model.LoginDto;
import com.rentalapp.model.RegisterDto;
import com.rentalapp.model.User;
import com.rentalapp.service.UserService;

import java.util.List;

/**
 * RestController for handling user-related requests.
 * Maps to /users URL path and provides endpoints to manage users.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public User loginUser(@RequestBody LoginDto user) {
    	System.out.println(user.toString());
        User foundUser = userService.getUserByUsername(user.getUserName());
        System.out.println(user.getUsername()+" "+foundUser.getUserName());

        if (foundUser != null && foundUser.getPassword().equals(user.getPassword())) {
            return foundUser;
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/add")
    public void addUser(@RequestBody RegisterDto user) {
        userService.addUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }
    
    @PutMapping("/deactivate/{userId}")
    public ResponseEntity<String> deactivateUser(@PathVariable int userId) {
        userService.deactivateUser(userId);
        return ResponseEntity.ok("User deactivated successfully.");
    }

    @PutMapping("/reactivate/{userId}")
    public ResponseEntity<String> reactivateUser(@PathVariable int userId) {
        userService.reactivateUser(userId);
        return ResponseEntity.ok("User reactivated successfully.");
    }
}
