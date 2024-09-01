package com.example.security1.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.security1.config.auth.PrincipalDetails;
import com.example.security1.model.User;
import com.example.security1.repo.UserRepository;

// 스프링 시큐리티는 자기만의 시큐리티 세션을 들고있다.
// 원래 기존 session 영역이 있다.
// 여기에 시큐리티가 관리하는 session이 따로 있다.
// 시큐리티가 관리하는 session에 들어갈 수 있는 타입은
// Authentication 객체밖에 없다. 들어가는 순간 로그인이 되는 것
// 필요할때마다 Controller에서 DI 해서 사용할 수 있다.
// 여기에 들어갈 수 있는 두개의 타입이 있는데
// 1. UserDetails 2. OAuth2User

@Controller // View를 리턴
public class IndexController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// OAuth2 로그인 후 테스트시 캐스팅 오류가 난다..
	@GetMapping("/test/login")
	public @ResponseBody String loginTest(Authentication authentication,
			// 세션 정보에 접근
			@AuthenticationPrincipal PrincipalDetails userDetails) { // DI(의존성 주입) 시큐리티는 세션영역에 시큐리티 컨텍스트에 세션 정보를 Authentication 객체로 저장
		System.out.println("/test/login =================");
		// Authentication을 다운캐스팅
		PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
		System.out.println("authentication : " + principalDetails.getUser());
		
		System.out.println("userDetails : " + userDetails.getUser());
		return "세션 정보 확인하기";
	}
	
	@GetMapping("/test/oauth/login")
	public @ResponseBody String testOAuthLogin(Authentication authentication,
			@AuthenticationPrincipal OAuth2User oauth) { // DI(의존성 주입) 시큐리티는 세션영역에 시큐리티 컨텍스트에 세션 정보를 Authentication 객체로 저장
		System.out.println("/test/login =================");
		// Authentication을 다운캐스팅이 안됨 OAuth2User로 받아야한다
		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
		System.out.println("authentication : " + oAuth2User.getAttributes());
		System.out.println("oauth2User : " + oauth.getAttributes());
		
		return "OAuth 세션 정보 확인하기";
	}
	
	// localhost:8080/
	// localhost:8080
	@GetMapping({"", "/"})
	public String index() {
		// 머스테치 기본폴터 src/main/resources/
		// 뷰리졸버 설정 : templates (prefix), .mustache (suffix) 생략 가능
		return "index"; // src/main/resources/templates/index.mustache
	}
	
	@GetMapping("/user")
	public @ResponseBody String user(
			// OAuth 일지 아닐지 판단 불가 그래서 하나로 묶어서 implement 받은 객체를 생성하여 사용
			// OAuth 도 PrincipalDetails 타입으로 묶어서 사용
			@AuthenticationPrincipal PrincipalDetails principalDetails
			) {
		return "user";
	}
	
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "admin";
	}
	
	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "manager";
	}
	
	// 스프링 시큐리티가 해당 주소를 낚아챔 - SecurityConfig 파일 생성 후엔 작동하지 않음
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}
	
	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	@PostMapping("/join")
	public String join(User user) {
		System.out.println(user);
		user.setRole("ROLE_USER");
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		
		userRepository.save(user); // 회원가입 하면 비밀번호가 1234로 들어감 => 시큐리티로 로그인 불가 이유는 패스워드가 암호화가 안되어서
		return "redirect:/loginForm"; // 위 함수 호출
	}
	
	@Secured("ROLE_ADMIN") // 특정 메서드에 간단하게 걸고 싶을 때
	@GetMapping("/info")
	public @ResponseBody String info() {
		return "개인정보";
	}
	
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')") // data() 가 실행되기 직전에 실행 여러개 걸 수 있다.
	@GetMapping("/data")
	public @ResponseBody String data() {
		return "데이터정보";
	}
}
