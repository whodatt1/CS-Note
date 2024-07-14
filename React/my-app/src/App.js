import logo from './logo.svg';
import './App.css';

// 0. React 엔진 - 데이터 변경 감지해서 UI를 그려주는 node.js 서버 위에서 실행된다. 데이터 변경을 감지해야 하기 때문에

// 1. 실행과정 (index.html) - SPA(싱글 페이지 어플리케이션) -> 리액트에서 페이지 이동이란 root div를 갈아 끼워넣는 것
// npm start -> index.html을 읽음 index.js에서 렌더 하면서..

// 2. JSX 문법
// javascript에서 html을 사용
// 모든걸 자바스크립트 파일로 관리 가능하다.

// (1) return시에 하나의 Dom만 리턴할 수 있다.
// (2) 변수 선언은 let 혹은 const로만 해야한다.
// (3) if 사용 불가능 X -> 삼항연산자 사용가능
// (4) 조건부 렌더링 && 는 false일 경우 출력을 안한다 true일 때만..
// (5) css디자인
//    - 내부에 적는 방법(비추천)
//    - 외부 파일에 적는 방법
//    - 라이브러리 사용 (부트스트랩, component-styled)

let a = 10; // 객체(변수)
const b = 20; // 상수

// 3. 바벨 (자바스크립트 ES5) -> ES6
// ES6로 개발하고 실제로는 ES5로 돌아가게끔

function App() {
  let c; // 선언만
  console.log(1, c); // undefined 실제 값 -> 정의되지 않은 변수를 나타냄

  // function hello() {
  //   // var b = 20; // 밖에서 쓸수 있게된다..
  //   let b = 20;
  // }

  // 스타일 변수
  const mystyle = {
    color: 'red',
  };

  // return의 괄호 한줄이면 괄호가 필요없지만 여러줄이면 괄호가 생긴다.
  return (
    <div>
      <div style={mystyle}>
        안녕 {a === 10 ? '10입니다.' : '10이 아닙니다.'}
      </div>
      <h1 className="box-style">해딩태그 {b === 20 && '20입니다.'}</h1>
      <hr />
    </div>
  );
}

export default App;
