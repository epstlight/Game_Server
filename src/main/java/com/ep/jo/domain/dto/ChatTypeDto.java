package com.ep.jo.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ChatTypeDto {
	
	public enum MessageType{
		LOGIN, MATCH
	}
	private MessageType type;
}
