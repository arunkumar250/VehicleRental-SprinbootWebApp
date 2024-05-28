package com.rentalapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rentalapp.model.Roles;

/*
 * This interface extends JpaRepository to provide CRUD operations for the User entity.
 * It also defines a custom query method findByUsername to fetch a user by their username.
 */
public interface RoleRepository extends JpaRepository<Roles, Integer> {
}
