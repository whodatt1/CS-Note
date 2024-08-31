package com.example.security1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.security1.model.User;

// CRUD 함수 내장
// @Repository 어노테이션 없어도 IoC가 된다. 이유는 JpaRepository를 상속했기 때문
public interface UserRepository extends JpaRepository<User, Integer> {
	// findBy 규칙 -> Username 문법
	// select * from user where username = 1?
	public User findByUsername(String username); // Jpa Query methods
}
