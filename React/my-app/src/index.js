import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

// public -> index.html에서 root를 찾는다
const root = ReactDOM.createRoot(document.getElementById('root'));
// 렌더링 한다.
root.render(
  // App.js
  <App />,
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
