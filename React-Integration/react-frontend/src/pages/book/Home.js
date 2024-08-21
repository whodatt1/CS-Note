import React, { useEffect, useState } from 'react';
import BookItem from '../../components/BookItem';

const Home = () => {
  const [books, setBooks] = useState([]);

  // 함수 실행 시 최초 한번 실행되는 것 + 상태값이 변경될때마다 실행
  useEffect(() => {
    // GET이 DEFAULT
    // IO 발생 NETWORK
    // Promise를 받고 들고있다가 완료되면 데이터를 넣어줘라 (빈 통장같이)
    // 비동기로 하는 이유는 html 그림을 그리는 동시에 데이터를 가져오기 위해
    fetch('http://localhost:8080/book')
      .then((res) => res.json()) // Promise
      .then((res) => {
        console.log(res);
        setBooks(res);
      }); // 비동기 함수
  }, []); // 한번만 실행되도록 빈배열 books 넣으면 무한루프가 걸림

  return (
    <div>
      {books.map((book) => (
        <BookItem key={book.id} book={book} /> /* 키 값을 넣어줘야 함 */
      ))}
    </div>
  );
};

export default Home;
