package com.ep.jo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ep.jo.domain.entity.UserEntity;
import com.ep.jo.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@Api(tags = {"1. User"})
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	
	@GetMapping("/info")
	@ApiOperation(value = "로그인", notes = "등록 x -> 신규, 등록 o -> 유저정보")
	public UserEntity userInfo(@RequestParam String email, @RequestParam String username) {
		if(userService.checkUser(email)) {
			return userService.findUser(email);
		}
		else {
			return userService.saveUser(email, username);
		}
	}
}
