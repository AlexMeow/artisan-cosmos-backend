package com.example.artisan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.artisan.model.po.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
