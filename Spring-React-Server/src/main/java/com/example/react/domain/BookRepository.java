package com.example.react.domain;

import org.springframework.data.jpa.repository.JpaRepository;

// @Repository 적어야 스프링 IoC에 빈으로 등록이 되는데
// JpaRepository를 extends하면 생략이 가능함
public interface BookRepository extends JpaRepository<Book, Long> {

}
