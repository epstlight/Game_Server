package com.ep.jo.config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.ep.jo.domain.dto.ChatMessageDto;
import com.ep.jo.domain.dto.ChatRoomDto;
import com.ep.jo.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
@RequiredArgsConstructor
public class WebSocketChatHandler extends TextWebSocketHandler {
	private final ObjectMapper objectMapper;
	private final ChatService chatService;
	
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String payload = message.getPayload();
		log.info("payload : {}", payload);
//		TextMessage textMessage = new TextMessage("welcomee chatting server");
//		session.sendMessage(textMessage);
		ChatMessageDto chatMessage = objectMapper.readValue(payload, ChatMessageDto.class);
		ChatRoomDto room = chatService.findRoomById(chatMessage.getRoomId());
		room.handleActions(session, chatMessage, chatService);
	}

}
