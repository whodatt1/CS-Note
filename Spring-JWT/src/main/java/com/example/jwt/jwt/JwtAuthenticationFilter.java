package com.example.jwt.jwt;

import java.io.BufferedReader;
import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.jwt.auth.PrincipalDetails;
import com.example.jwt.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

// 스프링 시큐리티에서 UsernamePasswordAuthenticationFilter 가 있음.
// /login 요청해서 usename, password 전송하면 (post)
// UsernamePasswordAuthenticationFilter 동작을 함.
// formlogin을 disable 했기 때문에 작동을 안함.
// 이걸 시큐리티 컨피그에 필터로 등록해야함
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager authenticationManager;
	
	// login 요청을 하면 로그인 시도를 위하여 실행되는 함수
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		System.out.println("JwtAuthenticationFilter : 로그인 시도중");
		
		// 1. username, pw 받아서
		try {
//			BufferedReader br = request.getReader();
//			
//			String input = null;
//			while ((input = br.readLine()) != null) {
//				System.out.println(input);
//			}
			ObjectMapper om = new ObjectMapper();
			
			User user = om.readValue(request.getInputStream(), User.class);
			System.out.println(user);
			
			// 토큰 만들기
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
			
			// PrincipalDetailsService에 loadUserByUsername()이 실행된 후 정상이면 authentication이 리턴됨.
			// DB에 있는 username과 password가 일치한다.
			Authentication authentication = 
					authenticationManager.authenticate(authenticationToken);
			
			PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
			
			System.out.println("로그인 완료됨 : " + principalDetails.getUser().getUsername()); // 로그인 정상적으로 되었다는 뜻
			
			// authentication 객체가 session 영역에 저장을 해야하고 그 방법이 return
			// 리턴의 이유는 권한 관리를 security가 대신 해주기 때문에 편하려고 하는 것
			// 굳이 JWT 토큰을 사용하면서 세션을 만들 이유가 없음 근데 단지 권한 처리 때문에 session에 넣어준다.
			
			return authentication;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// attemptAuthentication 싱행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수가 실행됨
	// JWT 토큰을 만들어서 request 요청한 사용자에게 JWT 토큰을 response 해주면 됨
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.out.println("successfulAuthentication 실행됨 : 인증 완료");
		super.successfulAuthentication(request, response, chain, authResult);
	}
}
