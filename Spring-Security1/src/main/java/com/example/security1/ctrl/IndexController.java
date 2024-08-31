package com.example.security1.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.security1.model.User;
import com.example.security1.repo.UserRepository;

@Controller // View를 리턴
public class IndexController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	// localhost:8080/
	// localhost:8080
	@GetMapping({"", "/"})
	public String index() {
		// 머스테치 기본폴터 src/main/resources/
		// 뷰리졸버 설정 : templates (prefix), .mustache (suffix) 생략 가능
		return "index"; // src/main/resources/templates/index.mustache
	}
	
	@GetMapping("/user")
	public @ResponseBody String user() {
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
