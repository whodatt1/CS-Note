package com.example.ws.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
	public enum MessageType {
		ENTER, TALK
	}
	
	private MessageType type;
	
	private String roomId;
	
	private String sender;
	
	private String message;
	
}
