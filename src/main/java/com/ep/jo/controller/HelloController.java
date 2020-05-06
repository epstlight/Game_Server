package com.ep.jo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1")
public class HelloController {
	
	@GetMapping
	public String hello() {
		return "hello";
	}
	
	@GetMapping("/jj")
	public String jj() {
		return "최준혀 병신";
	}
	
}
