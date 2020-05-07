package com.ep.jo.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDto {
	
	public enum MessageType{
		ENTER, TALK
	}
	private MessageType type;
	private String roomId;
	private String sender;
	private String message;
}
