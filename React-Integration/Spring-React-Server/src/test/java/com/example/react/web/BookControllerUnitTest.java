package com.example.react.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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
	
	@Test
	public void findAll_테스트() throws Exception {
		// given
		List<Book> books = new ArrayList<>();
		books.add(new Book(1L, "스프링 부트", "코스"));
		books.add(new Book(2L, "리액트", "코스"));
		
		when(bookService.findAll()).thenReturn(books);
		
		// when
		ResultActions resultActions = mockMvc.perform(get("/book")
				.accept(MediaType.APPLICATION_JSON_VALUE));
		
		// then
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", Matchers.hasSize(2)))
			.andExpect(jsonPath("$.[0].title").value("스프링 부트"))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void findById_테스트() throws Exception {
		// given
		Long id = 1L;
		when(bookService.getOne(id)).thenReturn(new Book(1L, "테스트", "킴"));
		
		// when
		ResultActions resultActions = mockMvc.perform(get("/book/{id}", id)
				.accept(MediaType.APPLICATION_JSON_VALUE));
		
		// then
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.title").value("테스트"))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void update_테스트() throws Exception {
		// given
		Long id = 1L;
		Book book = new Book(null, "C++ 따라하기", "코스");
		String content = new ObjectMapper().writeValueAsString(book);
		
		when(bookService.update(id, book)).thenReturn(new Book(1L, "C++ 따라하기", "코스"));
		
		// when (테스트 실행)
		ResultActions resultActions = mockMvc.perform(put("/book/{id}", id)
			   .contentType(MediaType.APPLICATION_JSON_VALUE) // 요청
			   .content(content) // 내용
			   .accept(MediaType.APPLICATION_JSON_VALUE)); // 응답
		
		// then
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.title").value("C++ 따라하기"))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void delete_테스트() throws Exception {
		// given
		Long id = 1L;
		
		when(bookService.delete(id)).thenReturn("ok");
		
		// when (테스트 실행)
		ResultActions resultActions = mockMvc.perform(delete("/book/{id}", id)
			   .accept(MediaType.TEXT_PLAIN_VALUE)); // 응답
		
		// then
		resultActions
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print());
		
		// 문자열을 응답할 경우
		MvcResult requestResult = resultActions.andReturn();
		String result = requestResult.getResponse().getContentAsString();
		
		assertEquals("ok", result);
	}
}
