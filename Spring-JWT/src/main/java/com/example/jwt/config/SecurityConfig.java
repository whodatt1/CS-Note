package com.example.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final CorsFilter corsFilter;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
			.sessionManagement((sessionManagement) ->
				sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				) // 세션 미사용
			.addFilter(corsFilter) // @CrossOrigin(인증x), 시큐리티 필터에 등록 인증(O)
			.formLogin(form -> form.disable())		 // 폼 로그인 방식 사용 x
			.httpBasic(HttpBasicConfigurer::disable) // http 로그인 방식 사용 x
			.authorizeHttpRequests((authz) -> authz
									.requestMatchers("/api/v1/user/**")
									.hasAnyRole("USER", "MANAGER", "ADMIN")
									.requestMatchers("/api/v1/manager/**")
									.hasAnyRole("MANAGER", "ADMIN")
									.requestMatchers("/api/v1/admin/**")
									.hasRole("ADMIN")
									.anyRequest().permitAll());
		
		return http.build();
	}
}
