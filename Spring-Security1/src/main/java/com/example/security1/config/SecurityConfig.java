package com.example.security1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true) // secure 어노테이션 활성화, preAuthorize, postAuthorize 어노테이션 활성화
public class SecurityConfig {
	
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
		
		return http.build();
	}
	
}
