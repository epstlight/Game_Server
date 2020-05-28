package com.ep.jo.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.ep.jo.domain.entity.RoomEntity;
import com.ep.jo.domain.repository.RoomReposiotry;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RoomService {
	private final ObjectMapper objectMapper;
	private final RoomReposiotry roomRepository;
	
	public RoomEntity checkWaitRoom(int matchUid) {
		List<RoomEntity> rooms = roomRepository.findAll();
		for(RoomEntity room : rooms) {
			if(room.getSecond_uid() == 0 && room.getFirst_uid() != matchUid) {
				return joinRoom(matchUid, room);
			}
		}
		return createRoom(matchUid);
    }
	
	public RoomEntity createRoom(int matchUid) {
        String randomId = UUID.randomUUID().toString();
        RoomEntity room = RoomEntity.builder()
                .roomId(randomId)
                .first_uid(matchUid)
                .build();
        return roomRepository.save(room);
    }
	
	public RoomEntity joinRoom(int matchUid, RoomEntity room) {
		room.setSecond_uid(matchUid);
		return roomRepository.save(room);
	}
	
	public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
	
}
