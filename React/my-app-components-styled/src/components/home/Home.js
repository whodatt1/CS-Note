import React from "react";
import styled from "styled-components";

// Styled-components를 밖으로 빼고 props로 받는다
const StyledDeleteButton = styled.button`
  color: ${(props) => (props.user.username === "kim" ? "blue" : "red")};
`;

// 상속받을 수 있다.. 위 버튼
const StyledAddButton = styled(StyledDeleteButton)`
  background-color: green;
`;

// Function 방식이라 가능하다.
// 부모로부터 받아온 어떤 데이터를 가지고 스타일링을 동적으로 할것이라면?
const Home = (props) => {
  // 구조분할 할당
  const { boards, setBoards, number, setNumber, user } = props;

  return (
    <div>
      <h1>홈 : {number}</h1>
      <StyledDeleteButton user={user}>유저삭제</StyledDeleteButton>
      <StyledAddButton user={user}>유저추가</StyledAddButton>
      <button onClick={() => setNumber(number + 1)}>번호증가</button>
      <button onClick={() => setBoards([])}>전체삭제</button>
      {boards.map((board) => (
        <h3>
          제목 : {board.title} 내용 : {board.content}
        </h3>
      ))}
    </div>
  );
};

export default Home;
