package shop.my.product.security;

import java.io.IOException;
import java.util.Iterator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


// Nginx 요청시에만 서버가 요청하도록 막는 시큐리티
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.addFilterBefore(new CustomHeaderFilter(), AbstractPreAuthenticatedProcessingFilter.class)
            .authorizeHttpRequests((authorize) -> authorize
            .anyRequest().permitAll())
            .csrf(csrf -> csrf.disable());
            ;

        return http.build();
    }

    public class CustomHeaderFilter implements Filter {

        @Override
        public void destroy() {}

        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
                throws IOException, ServletException {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;

            System.out.println(" 여기 !!! : " + httpServletRequest.getHeader("N-NginX-Proxy"));
            
            if ("true".equals(httpServletRequest.getHeader("N-NginX-Proxy"))) {
                chain.doFilter(httpServletRequest, httpServletResponse);
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                httpServletResponse.getWriter().write("Forbidden");
            }
        }

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {}
        
    }
}
