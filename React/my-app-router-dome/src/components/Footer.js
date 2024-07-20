import React from "react";
import styled from "styled-components";

// 하나의 컴포넌트를 생성 (재사용)

// styled-components => js 파일과 css파일이 합쳐진다 관리하기 편하다..
const StyledFooterList = styled.div`
  border: 1px solid black;
  height: 300px;
`;

const Footer = () => {
  return (
    <StyledFooterList>
      <ul>
        <li>오시는길 : 서울 특별시</li>
        <li>전화번호 : 020222</li>
      </ul>
    </StyledFooterList>
  );
};

export default Footer;
