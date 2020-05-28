package com.ep.jo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ep.jo.domain.entity.RoomEntity;
import com.ep.jo.domain.entity.UserEntity;

public interface RoomReposiotry extends JpaRepository<RoomEntity, Long> {
	RoomEntity findByRoomId(String roomId);
}
