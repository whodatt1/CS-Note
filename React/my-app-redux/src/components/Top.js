import React from "react";
import "../App.css";
import { useSelector } from "react-redux";

const Top = () => {
  // store.number 는 report 한 redux에 state에서 찾아서 온다.
  const { number, username } = useSelector((store) => store);

  return (
    <div className="sub-container">
      <h1>Top</h1>
      번호 : {number}
      이름 : {username}
    </div>
  );
};

export default Top;
