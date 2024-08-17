package com.example.react.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.react.domain.Book;
import com.example.react.domain.BookRepository;

import lombok.RequiredArgsConstructor;

// 기능을 정의할 수 있고 트랜잭션을 관리할 수 있다.

@RequiredArgsConstructor
@Service
public class BookService {

	private final BookRepository bookRepository;
	
	@Transactional // 서비스 함수가 종료될때 COMMIT OR ROLLBACK TRANSACTION 관리
	public Book save(Book book) {
		return bookRepository.save(book);
	}
	
	@Transactional(readOnly = true) // JPA 변경감지라는 내부 기능 활성화 X 내부 연산 줄임, update시의 정합성을 유지해줌. insert의 유령데이터현상(팬텀현상) 못막음
	public Book getOne(Long id) {
		return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id를 확인해주세요."));
	}
	
	@Transactional(readOnly = true)
	public List<Book> findAll() {
		return bookRepository.findAll();
	}
	
	@Transactional
	public Book update(Long id, Book book) {
		// 더티체킹 update 치기
		// 데이터베이스에서 들고온 객체
		Book bookEntity = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id를 확인해주세요.")); // 영속화(book 오브젝트) -> 영속성 컨텍스트 보관
		
		bookEntity.setTitle(book.getTitle());
		bookEntity.setAuthor(book.getAuthor());
		return bookEntity;
	} // 함수 종료 => 트랜잭션 종료 => 영속화 되어있는 데이터를 DB로 갱신(flush) => commit ========> 더티체킹
	
	@Transactional
	public String delete(Long id) {
		bookRepository.deleteById(id); // 오류가 터지면 익셉션을 탄다. 신경 쓰지말고
		return "ok";
	}
}
