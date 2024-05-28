package com.rentalapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rentalapp.model.User;
import com.rentalapp.repository.UserRepository;

/**
 * Data Access Object (DAO) for handling user-related database operations.
 * Uses UserRepository to perform CRUD operations on User entities.
 */
@Component
public class UserDao {

    private final UserRepository userRepository;

    /**
     * Constructor for UserDao, with UserRepository autowired.
     * This ensures that UserRepository is injected by the Spring framework.
     *
     * @param userRepository the repository to manage users
     */
    @Autowired
    public UserDao(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a user by their username.
     * @param userName the username of the user
     * @return the user with the specified username
     */
    public User getUserByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    /**
     * Retrieves all users from the database.
     * @return a list of all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Adds a new user to the database.
     *
     * @param user the user to be added
     */
    public void addUser(User user) {
    	System.out.println(user.toString());
        userRepository.save(user);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user
     * @return the user with the specified ID, or null if not found
     */
    public User getUserById(int id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    /**
     * Updates an existing user in the database.
     * If the user with the specified ID exists, it updates the user.
     *
     * @param id the ID of the user to update
     * @param updatedUser the updated user details
     * @return the updated user, or null if the user does not exist
     */
    public User updateUser(int id, User updatedUser) {
        if (userRepository.existsById(id)) {
            updatedUser.setId(id);
            return userRepository.save(updatedUser);
        }
        return null;
    }
    
    public void reactivateUser(int userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        boolean isActive = true;
        System.out.println(isActive);
        user.setActive(isActive); // Mark the user's account as active
        userRepository.save(user);
    }
    
    public void deactivateUser(int userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User not found"));
        boolean isActive = false;
        user.setActive(isActive); // Mark the user's account as deactivated
        userRepository.save(user);
    }
}
