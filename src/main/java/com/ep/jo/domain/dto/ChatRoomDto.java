package com.ep.jo.domain.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.WebSocketSession;

import com.ep.jo.service.ChatService;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ChatRoomDto {
	private String roomId;
	private String name;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Set<WebSocketSession> sessions = new HashSet<WebSocketSession>();
	private Set<String> senders = new HashSet<>();

	@Builder
	public ChatRoomDto(String roomId, String name) {
		this.name = name;
		this.roomId = roomId;
	}

	public void handleActions(WebSocketSession session, ChatMessageDto chatMessage, ChatService chatService) {
		if (chatMessage.getType().equals(ChatMessageDto.MessageType.ENTER)) {
			sessions.add(session);
			senders.add(chatMessage.getSender());
			chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
		}
		sendMessage(chatMessage, chatService);
	}
	
	public <T> void sendMessage(T message, ChatService chatService) {
        sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
    }

}
