package com.ep.jo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ep.jo.domain.entity.AdminEntity;


public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
	AdminEntity findByEmail(String email);
}
