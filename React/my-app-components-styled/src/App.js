import logo from "./logo.svg";
import "./App.css";
import { Title } from "./MyCss";
import LoginPage from "./pages/LoginPage";
import HomePage from "./pages/HomePage";

// 자동 완성도 안되고 불편하다.. 함수 내부에 넣으면 안됨
// 디자인은 정적인 것 함수 내에 넣으면 그림그릴때마다 계속 읽어진다.
// css를 import 받아서 하는것을 추천
const a = {
  backgroundColor: "red",
};

// npm install styled-components

function App() {
  return (
    <div>
      {/* <div style={a}>안녕</div>
      <div className="box-style">헬로</div>
      <Title>헬로</Title>
       <LoginPage /> */}
      <HomePage />
    </div>
  );
}

export default App;
