package com.ep.jo.domain.dto;

import com.ep.jo.domain.dto.ChatTypeDto.MessageType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginDto {
	private String username;
	private String email;
}
