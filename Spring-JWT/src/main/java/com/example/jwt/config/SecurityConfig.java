package com.example.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.web.filter.CorsFilter;

import com.example.jwt.filter.MyFilter3;
import com.example.jwt.jwt.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final CorsFilter corsFilter;
	
	@Bean
	BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
		http.addFilterBefore(new MyFilter3(), SecurityContextHolderFilter.class);
		http.csrf(AbstractHttpConfigurer::disable)
			.sessionManagement((sessionManagement) ->
				sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				) // 세션 미사용
			.addFilter(corsFilter) // @CrossOrigin(인증x), 시큐리티 필터에 등록 인증(O)
			.formLogin(form -> form.disable())		 // 폼 로그인 방식 사용 x
			.httpBasic(HttpBasicConfigurer::disable) // http 로그인 방식 사용 x
			.addFilter(new JwtAuthenticationFilter(authenticationManager)) // AuthenticationManager를 던져줘야 함
			.authorizeHttpRequests((authz) -> authz
									.requestMatchers("/api/v1/user/**")
									.hasAnyRole("USER", "MANAGER", "ADMIN")
									.requestMatchers("/api/v1/manager/**")
									.hasAnyRole("MANAGER", "ADMIN")
									.requestMatchers("/api/v1/admin/**")
									.hasRole("ADMIN")
									.anyRequest().permitAll()
								   );
		
		return http.build();
	}
}
