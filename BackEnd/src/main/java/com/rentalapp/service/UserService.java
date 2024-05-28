/*
 * This class represents the service layer for Users.
 * It contains methods to interact with users, such as retrieving, adding, updating, and deleting users.
 */
package com.rentalapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rentalapp.dao.UserDao;
import com.rentalapp.model.RegisterDto;
import com.rentalapp.model.Roles;
import com.rentalapp.model.User;
import com.rentalapp.repository.RoleRepository;

@Service
public class UserService {

    private final UserDao userDao;
    private RoleRepository roleRepo;

    /*
     * Constructs a UserService with the provided UserDao.
     * @param userDao The data access object for Users.
     */
    @Autowired
    public UserService(UserDao userDao, RoleRepository roleRepo) {
        this.userDao = userDao;
        this.roleRepo = roleRepo;
    }
    
    /*
     * Retrieves a user by username from the database.
     * @param userName The username of the user to retrieve.
     * @return The user with the specified username, or null if not found.
     */
    public User getUserByUsername(String userName) {
        return userDao.getUserByUsername(userName);
    }

    /*
     * Retrieves all users from the database.
     * @return A list of all users.
     */
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    /*
     * Adds a new user to the database.
     * @param user The user to add.
     */
    public void addUser(RegisterDto userDto) {
    	User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        Roles role = roleRepo.getById(userDto.getRoleId());
        user.setRole(role);
        user.setActive(userDto.getActive());
        userDao.addUser(user);
    }

    /*
     * Retrieves a user by ID from the database.
     * @param id The ID of the user to retrieve.
     * @return The user with the specified ID, or null if not found.
     */
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    /*
     * Updates an existing user in the database.
     * @param id The ID of the user to update.
     * @param updatedUser The updated user object.
     * @return The updated user object, or null if the user was not found.
     */
    public User updateUser(int id, User updatedUser) {
        return userDao.updateUser(id, updatedUser);
    }
    
    public void deactivateUser(int userId) {
    	userDao.deactivateUser(userId);
    }
    
    public void reactivateUser(int userId) {
    	userDao.reactivateUser(userId);
    }
}
