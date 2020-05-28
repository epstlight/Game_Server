package com.ep.jo.config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.ep.jo.domain.dto.ChatTypeDto;
import com.ep.jo.domain.dto.MatchDto;
import com.ep.jo.domain.dto.UserLoginDto;
import com.ep.jo.service.WebSocketService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {
	private final ObjectMapper objectMapper;
	private final WebSocketService webSocketService;
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		log.info("payload : {}", payload);
		ChatTypeDto chatType = objectMapper.readValue(payload, ChatTypeDto.class);
		
		// type:LOGIN -> 로그인
		if (chatType.getType().equals(ChatTypeDto.MessageType.LOGIN)) {
			UserLoginDto user = objectMapper.readValue(payload, UserLoginDto.class);
			webSocketService.login(session, user);
		}
		else if(chatType.getType().equals(ChatTypeDto.MessageType.MATCH)){
			MatchDto matchUid = objectMapper.readValue(payload, MatchDto.class);
			webSocketService.match(session, matchUid);
		}
		
	}

}
