package com.example.react.ctrl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.react.domain.Book;
import com.example.react.service.BookService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BookController {
	
	private final BookService bookService;
	
	// security (라이브러리 적용) - CORS 정책을 가지고 있다. (시큐리티가 CORS를 해제)
	@CrossOrigin // 외부 자바스크립트 요청 허용 컨트롤러 진입 직전 동작
	@PostMapping("/book")
	// 파라미터 @RequestBody => raw => JSON 형식
	// 안붙으면 x-www-form-urlencoded 방식의 MIME TYPE
	public ResponseEntity<?> save(@RequestBody Book book) {
		return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED); // 201
	}
	
	@CrossOrigin // 외부 자바스크립트 요청 허용
	@GetMapping("/book")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK); // 200
	}
	
	@CrossOrigin // 외부 자바스크립트 요청 허용
	@GetMapping("/book/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(bookService.getOne(id), HttpStatus.OK); // 200
	}
	
	@CrossOrigin // 외부 자바스크립트 요청 허용
	@PutMapping("/book/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Book book) {
		return new ResponseEntity<>(bookService.update(id, book), HttpStatus.OK); // 200
	}
	
	@CrossOrigin // 외부 자바스크립트 요청 허용
	@DeleteMapping("/book/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		return new ResponseEntity<>(bookService.delete(id), HttpStatus.OK); // 200
	}
}
