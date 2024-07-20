import React from "react";
import Login from "../components/login/Login";
import { useNavigate, useParams } from "react-router-dom";

// React 6 부터는 useParams를 사용하여 넘겨진 url로 넘겨진 데이터를 확인
const LoginPage = () => {
  console.log("LoginPage", useParams());
  const navigate = useNavigate();
  return (
    <div>
      <button onClick={(e) => navigate(-1)}>뒤로가기</button>
      <Login />
    </div>
  );
};

export default LoginPage;
