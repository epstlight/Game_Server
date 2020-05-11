package com.ep.jo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ep.jo.domain.entity.AdminEntity;
import com.ep.jo.service.AdminService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
	private final AdminService adminService;
	
	@PostMapping("/join")
	public AdminEntity join(@RequestBody AdminEntity member) {
		return adminService.join(member);
	}
	
	@GetMapping("/login/success")
	public String loginSuccess() {
		return "Login Success!";
	}
	
	@GetMapping("/login/fail")
	public String loginFail() {
		return "Login Fail!";
	}
}
