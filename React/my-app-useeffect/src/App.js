import logo from "./logo.svg";
import "./App.css";
import { useEffect, useState } from "react"; // 해당 파일의 main default export가 아닌 것

function App() {
  const [data, setData] = useState(0);
  // 실행시점 :
  // (1) App() 함수가 최초 실행될 때 (App() 그림이 그려질 때)
  // (2) 상태 변수가 변경될 때
  useEffect(() => {
    console.log("useEffect 실행됨");
  });

  return (
    <div>
      <h1>데이터 : {data}</h1>
      <button
        onClick={() => {
          setData(data + 1);
        }}
      >
        더하기
      </button>
    </div>
  );
}

export default App;
