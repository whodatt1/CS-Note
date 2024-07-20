import React, { useEffect, useState } from "react";
import Header from "../components/Header";
import Footer from "../components/Footer";
import Home from "../components/home/Home";

// 자식에게 데이터를 넘기는 기법을 props라고 한다 (props를 패싱한다)
// Passing Data Flow => 부모 ----> 자식
const HomePage = () => {
  // http 요청 (fetch, axios(다운)) 웬만하면 Page가 들고있어야 한다.
  const [boards, setBoards] = useState([]);

  const [number, setNumber] = useState(0);

  const [user, setUser] = useState([]);

  useEffect(() => {
    // 다운로드 가정 => 다운로드 하는동안 Blocking이 되는데 fetch(), axios(), ajax() 는 비동기로 실행이 된다.
    let data = [
      { id: 1, title: "제목1", content: "내용1" },
      { id: 2, title: "제목2", content: "내용2" },
      { id: 3, title: "제목3", content: "내용3" },
    ];

    // 빈데이터
    setBoards([...data]);
    setUser({ id: 1, username: "kim" });
  }, []);

  return (
    <div>
      <Header />
      <Home
        boards={boards}
        setBoards={setBoards}
        number={number}
        setNumber={setNumber}
        user={user}
      />
      {/* props (속성) 상태데이터를 던져야 다시 그려질 수 있다 여러개를 넘길 수 있음 */}
      <Footer />
    </div>
  );
};

export default HomePage;
