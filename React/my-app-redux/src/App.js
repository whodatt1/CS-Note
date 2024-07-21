import logo from "./logo.svg";
import React, { useState } from "react";
import "./App.css";
import Top from "./components/Top";
import Bottom from "./components/Bottom";

// 촉매제를 던졌을때 다양한 화면에서 반응을 해줘야 할 때만 사용
function App() {
  return (
    <div className="container">
      <h1>최상단 화면</h1>
      <Top />
      <Bottom />
    </div>
  );
}

export default App;
