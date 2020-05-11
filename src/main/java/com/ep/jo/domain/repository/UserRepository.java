package com.ep.jo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ep.jo.domain.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByEmail(String email);
	boolean existsByEmail(String email);
}
