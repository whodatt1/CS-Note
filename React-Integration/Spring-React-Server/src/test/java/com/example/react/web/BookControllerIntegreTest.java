package com.example.react.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import com.example.react.domain.Book;
import com.example.react.domain.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

/**
 * 통합 테스트 (컨트롤러로 전체 스프링을 테스트한다.)
 * 모든 Bean들을 똑같이 IoC 올리고 테스트 하는 것 유닛 테스트보다 무겁다
 * 
 * WebEnvironment.MOCK = 실제 톰캣을 올리는게 아니라, 가짜 톰캣으로 테스트
 * WebEnvironment.RANDOM_PORT = 실제 톰캣으로 테스트
 * @AutoConfigureMockMvc = MockMvc를 Ioc에 등록해준다.
 * @Transactional = 각각의 테스트 함수가 종료될 때마다 트랜잭션을 rollback 시켜주는 어노테이션
 */

@Slf4j
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK) // 실제 톰캣을 올리는게 아니라, 다른 톰캣으로 테스트 
public class BookControllerIntegreTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private EntityManager entityManager; // jpa는 이것의 구현체
	
	@BeforeEach // 모든 테스트 함수가 실행되기 전에 실행
	public void init() {
		// AUTO_INCREMENT를 1로 초기화
		entityManager.createNativeQuery("ALTER TABLE book AUTO_INCREMENT = 1").executeUpdate();
	}
	
	// BDDMockito 패턴 given, when, then
	@Test
	public void save_테스트() throws Exception {
		// given (테스트를 하기 위한 준비)
		Book book = new Book(null, "스프링 따라하기", "코스");
		String content = new ObjectMapper().writeValueAsString(book); // Object를 JSON으로 바꾸는 함수
		
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
		books.add(new Book(null, "스프링 부트", "코스"));
		books.add(new Book(null, "리액트", "코스"));
		books.add(new Book(null, "JUNIT5", "코스"));
		
		bookRepository.saveAll(books);
		
		// when
		ResultActions resultActions = mockMvc.perform(get("/book")
				.accept(MediaType.APPLICATION_JSON_VALUE));
		
		// then
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[0].id").value(1L))
			.andExpect(jsonPath("$", Matchers.hasSize(3)))
			.andExpect(jsonPath("$.[2].title").value("JUNIT5"))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void findById_테스트() throws Exception {
		// given
		Long id = 1L;
		
		List<Book> books = new ArrayList<>();
		books.add(new Book(null, "스프링 부트", "코스"));
		books.add(new Book(null, "리액트", "코스"));
		books.add(new Book(null, "JUNIT5", "코스"));
		
		bookRepository.saveAll(books);
		
		// when
		ResultActions resultActions = mockMvc.perform(get("/book/{id}", id)
				.accept(MediaType.APPLICATION_JSON_VALUE));
		
		// then
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.title").value("스프링 부트"))
			.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void update_테스트() throws Exception {
		// given
		Long id = 1L;
		
		List<Book> books = new ArrayList<>();
		books.add(new Book(null, "스프링 부트", "코스"));
		books.add(new Book(null, "리액트", "코스"));
		books.add(new Book(null, "JUNIT5", "코스"));
		
		bookRepository.saveAll(books);
		
		Book book = new Book(null, "C++ 따라하기", "코스");
		String content = new ObjectMapper().writeValueAsString(book);
		
		// when (테스트 실행)
		ResultActions resultActions = mockMvc.perform(put("/book/{id}", id)
			   .contentType(MediaType.APPLICATION_JSON_VALUE) // 요청
			   .content(content) // 내용
			   .accept(MediaType.APPLICATION_JSON_VALUE)); // 응답
		
		// then
		resultActions
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.id").value(1L))
			.andExpect(jsonPath("$.title").value("C++ 따라하기"))
			.andDo(MockMvcResultHandlers.print());
	}
}
