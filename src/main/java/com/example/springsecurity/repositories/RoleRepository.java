package com.example.springsecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springsecurity.entities.Role;

import org.springframework.stereotype.Repository;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Additional query methods can be defined here if needed
  
}
