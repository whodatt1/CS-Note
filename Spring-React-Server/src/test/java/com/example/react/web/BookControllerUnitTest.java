package com.example.react.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.example.react.domain.Book;
import com.example.react.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

// 단위 테스트 (컨트롤러만 테스트 한다.)
// Controller 관련 로직만 띄우기 Filter, ControllerAdvice(익셉션 처리) 가볍다

@Slf4j
@WebMvcTest // Controller를 위한 테스트 어노테이션
public class BookControllerUnitTest {
	
	@Autowired
	private MockMvc mockMvc; // 주소 호출을 하여 테스트해주는 도구
	
	@MockBean // IoC 환경에 bean 등록됨.
	private BookService bookService;
	
	// BDDMockito 패턴 given, when, then
	@Test
	public void save_테스트() throws Exception {
		// given (테스트를 하기 위한 준비)
		Book book = new Book(null, "스프링 따라하기", "코스");
		String content = new ObjectMapper().writeValueAsString(book); // Object를 JSON으로 바꾸는 함수
		when(bookService.save(book)).thenReturn(new Book(1L, "스프링 따라하기", "코스"));
		
		// when (테스트 실행)
		ResultActions resultActions = mockMvc.perform(post("/book")
			   .contentType(MediaType.APPLICATION_JSON_VALUE) // 요청
			   .content(content) // 내용
			   .accept(MediaType.APPLICATION_JSON_VALUE)); // 응답
		
		// then (검증)
		resultActions
			.andExpect(status().isCreated())
			.andExpect(jsonPath("$.title").value("스프링 따라하기"))
			.andDo(MockMvcResultHandlers.print());
			
	}
}
