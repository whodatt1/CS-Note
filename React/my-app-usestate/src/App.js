import { useState } from "react";
import "./App.css";
import Sub from "./Sub";

function App() {
  // let number = 1; // 상태값 아님 렌더링이 다시 안된다. 상태값 변경이 아니기 때문에
  // 변수는 number 만들어지고 setNumber로 number를 변경 1이 number에 들어간다.
  // 레퍼런스 변경이 되어야 무조건 동작을 한다.
  const [number, setNumber] = useState(2); // React안에 hooks 라이브러리 사용 (상태값이 됨)

  console.log("App 실행됨");

  const [num, setNum] = useState(5);

  let sample = [
    { id: 1, name: "홍길동" },
    { id: 2, name: "임꺽정" },
    { id: 3, name: "장보고" },
    { id: 4, name: "김두한" },
  ];

  const [users, setUsers] = useState(sample);

  const download = () => {
    // 이 샘플을 useState 상단으로 빼면 레퍼런스가 동일하여 렌더링이 안된다.
    // let sample = [
    //   { id: 1, name: '홍길동' },
    //   { id: 2, name: '임꺽정' },
    //   { id: 3, name: '장보고' },
    //   { id: 4, name: '김두한' },
    // ];

    // sample.push({ id: 5, name: '시라소니' }); // 이거 푸쉬하고 sample을 넣어도 변경이 안됨 왜냐면 레퍼런스 변경이 안되었기 때문이다. 즉 여기선 깊은복사인 concat을 사용해야한다.
    // 클릭을 계속해도 화면에서는 시라소니 하나만 추가된다. 기존 위에 sample 데이터는 변하지 않았기 때문이다.
    // const a = sample.concat({ id: 5, name: '시라소니' });
    // setUsers(a);

    setUsers([...sample, { id: num, name: "시라소니" }]);
    setNum(num + 1);

    // setUsers([...users, ...sample]); // 기존데이터에 추가
    // setUsers(sample); // 기존데이터 상관없이 덮어쓰기 데이터는 같지만 다른 레퍼런스라서 다시 렌더링된다.
    // setUsers([...sample]) // 샘플을 useState 상단으로 빼도 이렇게 하면 레퍼런스가 달라짐 즉 다시 렌더링
  };

  const add = () => {
    // number++;
    setNumber(number + 1); // ++은 자신에게 다시 값을 넣는것이라 안된다. 이것은 리액트에게 변경해달라고 요청하는 것
    console.log("add", number);
  };

  return (
    <div>
      <div>
        <h1>숫자 : {number}</h1>
        {/* 바인딩만 add() 하게되면 실행이 되버림 */}
        <button onClick={add}>더하기</button>
        {/* 자식 그릴건지 안그릴건지 분리시킬수가 있어진다. */}
        <Sub />

        <button onClick={download}>다운로드</button>
        {users.map((u) => (
          <h1>
            {u.id}, {u.name}
          </h1>
        ))}
      </div>
    </div>
  );
}

export default App;
