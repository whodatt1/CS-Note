import logo from "./logo.svg";
import "./App.css";
import { Route, Routes } from "react-router-dom";
import ListPage from "./pages/ListPage";
// 글쓰기, 글삭제, 글목록보기

function App() {
  return (
    <div>
      <ListPage />
    </div>
  );
}

export default App;
