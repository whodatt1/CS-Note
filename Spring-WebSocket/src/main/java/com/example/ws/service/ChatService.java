package com.example.ws.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.ws.dto.ChatRoom;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatService {
	
	private Map<String, ChatRoom> chatRooms;
	
	@PostConstruct
	private void init() {
		chatRooms = new LinkedHashMap<>();
	}
	
	public List<ChatRoom> findAllRoom() {
		List<ChatRoom> result = new ArrayList<>(chatRooms.values());
		Collections.reverse(result);
		
		return result;
	}
	
	public ChatRoom findById(String roomId) {
		return chatRooms.get(roomId);
	}
	
	public ChatRoom createRoom(String name) {
		ChatRoom chatRoom = ChatRoom.create(name);
		chatRooms.put(chatRoom.getRoomId(), chatRoom);
		
		return chatRoom;
	}
}
