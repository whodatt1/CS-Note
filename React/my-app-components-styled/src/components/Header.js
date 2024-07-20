import React from "react";
import styled from "styled-components";

// 하나의 컴포넌트를 생성 (재사용)

// styled-components => js 파일과 css파일이 합쳐진다 관리하기 편하다..
const HeaderList = styled.div`
  border: 1px solid black;
  height: 300px;
`;

const StyledHeaderButtonDiv = styled.div`
  border: 1px solid black;
  height: 300px;
  background-color: ${(props) => props.backgroundColor};
`;

const Header = () => {
  return (
    <StyledHeaderButtonDiv backgroundColor={"blue"}>
      <ul>
        <li>메뉴 1</li>
        <li>메뉴 2</li>
      </ul>
    </StyledHeaderButtonDiv>
  );
};

export default Header;
