package com.ep.jo.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ep.jo.domain.entity.UserEntity;
import com.ep.jo.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	
	public UserEntity findUser(String email) {
		return userRepository.findByEmail(email);
	}
	
	public boolean checkUser(String email) {
		return userRepository.existsByEmail(email);
	}
	
	@Transactional
	public UserEntity saveUser(String email, String username) {
		return userRepository.save(UserEntity.builder()
				.email(email)
				.username(username)
				.build());
	}
}
