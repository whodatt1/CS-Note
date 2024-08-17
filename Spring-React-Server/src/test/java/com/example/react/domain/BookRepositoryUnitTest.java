package com.example.react.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

// 단위 테스트
// DB 관련된 Bean이 IoC에 등록되면 된다.

@Transactional
@AutoConfigureTestDatabase(replace = Replace.ANY) // Replace.ANY => 가짜 DB로 테스트, Replace.NONE => 실제 DB로 테스트
@DataJpaTest // Repository들을 다 IoC에 등록해준다.
public class BookRepositoryUnitTest {
	
	@Autowired
	private BookRepository bookRepository;
}
