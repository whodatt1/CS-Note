package com.example.jwt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyFilter3 implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		// 토큰 : kim 이것을 만들어줘야 한다. id와 pw가 정상적으로 들어와서 로그인이 완료 되면 토큰을 만들어 주고 그것을 응답을 해준다.
		// 요청할 때 마다 header에 Authorization에 value값으로 토큰을 가지고 온다.
		// 그 때 토큰이 넘어오면 이 토큰이 내가 만든 토큰이 맞는지만 검증하면 된다. (RSA, HS256)
		if (req.getMethod().equals("POST")) {
			System.out.println("POST 요청됨");
			String headerAuth = req.getHeader("Authorization");
			System.out.println(headerAuth);
			System.out.println("필터3");
			
			if (headerAuth.equals("kim")) {
				chain.doFilter(req, res);
			} else {
				PrintWriter outPrintWriter = res.getWriter();
				
				outPrintWriter.println("인증 안됨");
			}
		}
	}
	
	
}
