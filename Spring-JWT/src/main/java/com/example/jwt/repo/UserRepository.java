package com.example.jwt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jwt.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);
}
