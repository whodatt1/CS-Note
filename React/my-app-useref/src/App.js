// useRef(디자인)
// dom을 변경할 때 사

import logo from "./logo.svg";
import "./App.css";
import { createRef, useRef, useState } from "react";

function App() {
  const myRef = useRef(null);

  const [list, setList] = useState([
    { id: 1, name: "길동" },
    { id: 2, name: "꺽정" },
  ]);

  // 배열로 만들어서 사용
  const myRefs = Array.from({ length: list.length }).map(() => createRef()); // 동적으로 레퍼런스 생성
  return (
    <div>
      <button
        onClick={() => {
          console.log(myRef);
          console.log(myRef.current);
          myRef.current.style.backgroundColor = "red";
          myRefs[1].current.style.backgroundColor = "red";
        }}
      >
        색 변경
      </button>
      <div ref={myRef}>박스</div>
      {list.map((user, index) => (
        <h1 ref={myRefs[index]}>{user.name}</h1>
      ))}
    </div>
  );
}

export default App;
