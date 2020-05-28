package com.ep.jo.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import com.ep.jo.domain.dto.MatchDto;
import com.ep.jo.domain.dto.RoomDto;
import com.ep.jo.domain.dto.UserLoginDto;
import com.ep.jo.domain.entity.RoomEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WebSocketService {
	private Set<WebSocketSession> sessions;
	private final RoomService roomService;
	private final UserService userService;
	private Map<String, List<WebSocketSession>> sessionTable;

	@PostConstruct
	private void init() {
		sessions =  new HashSet<WebSocketSession>();
		sessionTable =  new HashMap<>();
	}
	
	
	public void login(WebSocketSession session, UserLoginDto user) {
		sessions.add(session);
		String email = user.getEmail();
		String username = user.getUsername();
		if(userService.checkUser(email)) {
			roomService.sendMessage(session, userService.findUser(email).toDto());
		}
		else {
			roomService.sendMessage(session, userService.saveUser(email, username).toDto());
		}
	}
	
	public void match(WebSocketSession session, MatchDto matchUid) {
		RoomEntity room = roomService.checkWaitRoom(matchUid.getUid());
		RoomDto roomDto = RoomDto.builder()
				.type(RoomDto.MessageType.MATCH)
				.roomId(room.getRoomId())
				.first_uid(room.getFirst_uid())
				.second_uid(room.getSecond_uid())
				.build();
		String roomId = roomDto.getRoomId();
		List<WebSocketSession> temp;
		if(sessionTable.containsKey(roomId)) {
			temp = sessionTable.get(roomId);
			temp.add(session);
			sessionTable.put(roomId, temp);
		}
		else {
			temp = new LinkedList<WebSocketSession>();
			temp.add(session);
			sessionTable.put(roomId, temp);
		}
		
		if(roomDto.getSecond_uid() == 0) {
			roomDto.setState(RoomDto.StateType.WAIT);
		}
		else {
			roomDto.setState(RoomDto.StateType.START);
		}
		
		sendMessage(roomDto, temp);
	}
	

	public <T> void sendMessage(T message, List<WebSocketSession> sessionss) {
		sessionss.parallelStream().forEach(session -> roomService.sendMessage(session, message));
	}
}
