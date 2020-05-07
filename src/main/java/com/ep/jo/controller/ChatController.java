package com.ep.jo.controller;


import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ep.jo.domain.dto.ChatRoomDto;
import com.ep.jo.service.ChatService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
	
	private final ChatService chatService;
	
	@PostMapping
	public ChatRoomDto createRoom(@RequestParam String name) {
		return chatService.createRoom(name);
	}
	
	@GetMapping
	public List<ChatRoomDto> findAllRoom(){
		return chatService.findAllRoom();
	}
	
	
}
