import logo from "./logo.svg";
import "./App.css";
import { useEffect, useState } from "react"; // 해당 파일의 main default export가 아닌 것

function App() {
  const [data, setData] = useState(0);
  const [search, setSearch] = useState(0);

  const download = () => {
    // 다운로드 받고 (통신)
    let downloadData = 5; // 가정
    setData(downloadData);
  };
  // 실행시점 :
  // (1) App() 함수가 최초 실행될 때 (App() 그림이 그려질 때)
  // (2) 상태 변수가 변경될 때 (그게 dependencyList에 등록되어 있어야함)

  useEffect(() => {
    // search의 상태값이 변경이 되면 실행이 된다. 2인 상태로 한번 초기화 후 다시 초기화 하려면 안됨 기존 값이 2기 때문에
    console.log("useEffect 실행됨");
    download(); // 계속 5가 된다. 버튼을 눌러도 버튼을 누를때마다 useEffect가 실행되어서
  }, [search]); // 두번째 인자는 어디에 의존하여 실행될 것인가.. 빈 배열을 넣으면 어디에도 의존하지 않아서 최초에 한번만 실행이 된다.

  return (
    <div>
      <h1>검색</h1>
      <button
        onClick={() => {
          setSearch(2);
        }}
      >
        검색하기
      </button>
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
