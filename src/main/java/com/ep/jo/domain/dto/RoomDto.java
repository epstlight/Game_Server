package com.ep.jo.domain.dto;

import com.ep.jo.domain.entity.RoomEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoomDto {
	public enum MessageType{
		LOGIN, MATCH
	}
	
	public enum StateType{
		WAIT, START
	}
	private MessageType type;
	private StateType state;
	private String roomId;
	private int first_uid;
	private int second_uid;

}
