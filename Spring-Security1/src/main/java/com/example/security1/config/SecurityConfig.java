package com.example.security1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.security1.config.oauth2.PrincipalOauth2UserService;

//1. 코드받기(인증), 2. 액세스토큰(권한)
//3. 사용자프로필 정보를 가져와서 4-1. 그 정보를 토대로 회원가입을 자동으로 진행시키도 한다.
//4-2. (이메일, 전화번호, 이름, 아이디) 쇼핑몰 -> (집주소), 백화점몰 -> (vip등급, 일반등급)

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true) // secure 어노테이션 활성화, preAuthorize, postAuthorize 어노테이션 활성화
public class SecurityConfig {
	
	@Autowired
	private PrincipalOauth2UserService principalOauth2UserService;
	
	@Bean // 해당 메서드의 리턴되는 오브젝트 IoC 등록
	BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests((authz) -> authz
					.requestMatchers("/user/**").authenticated() // 인증만 되면 들어갈 수 있는 주소!!
					.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN") // 권한
					.requestMatchers("/admin/**").hasRole("ADMIN")
					.anyRequest().permitAll()); // 나머지 허용
		
		http.formLogin(form -> form.loginPage("/loginForm")
				.loginProcessingUrl("/login") // login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해준다.
				.defaultSuccessUrl("/")
				);
		
		http.oauth2Login(oauth2 -> 
						// 구글 로그인이 완료된 뒤의 후처리가 필요하다. Tip. 코드를 받지 않고 (액세스토큰 + 사용자프로필정보를 한방에 받음)
						oauth2.loginPage("/loginForm") 
							  .userInfoEndpoint(userInfo -> userInfo
									  .userService(principalOauth2UserService))
						); 
		
		return http.build();
	}
	
}
