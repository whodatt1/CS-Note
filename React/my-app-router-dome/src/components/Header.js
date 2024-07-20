import React from "react";
import { Container, Nav, Navbar } from "react-bootstrap";
import { Link } from "react-router-dom";
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

// styled에 없다면 이런식으로 상속하여 쓰면된다.
const StyledHeaderLink = styled(Link)`
  color: red;
`;

// a 태그를 쓰면 전체를 다시 불러온다.. 현재는 가운데만 바뀌면 되는 상황
// 리액트는 처음 로딩이 오래걸리는데 a 태그를 쓰면 해당 작업을 계속하게 되는 것과 동일
// Link를 사용한다
const Header = () => {
  return (
    <div>
      <StyledHeaderButtonDiv backgroundColor={"blue"}>
        <ul>
          <li>
            <StyledHeaderLink to="/">홈</StyledHeaderLink>
          </li>
          <li>
            <StyledHeaderLink to="/login/10">로그인</StyledHeaderLink>
          </li>
        </ul>
      </StyledHeaderButtonDiv>

      <Navbar bg="dark" data-bs-theme="dark">
        <Container>
          <Navbar.Brand href="#home">aaaaaaaaaaa</Navbar.Brand>
          <Nav className="me-auto">
            <Link to="/" className="nav-link">
              홈
            </Link>
            {/*이거로 쓰지말고 위처럼 Link 사용하여*/}
            <Nav.Link href="/">Home</Nav.Link>{" "}
            <Nav.Link href="/login">Features</Nav.Link>
            <Nav.Link href="#pricing">Pricing</Nav.Link>
          </Nav>
        </Container>
      </Navbar>
    </div>
  );
};

export default Header;
