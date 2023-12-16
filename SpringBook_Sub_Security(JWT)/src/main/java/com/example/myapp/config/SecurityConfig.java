package com.example.myapp.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.myapp.jwt.JwtAuthenticationFilter;
import com.example.myapp.jwt.JwtTokenProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    JwtTokenProvider jwtTokenProvider() {
    	return new JwtTokenProvider();
    }
    
    @Bean
    JwtAuthenticationFilter authenticationFilter() {
    	return new JwtAuthenticationFilter(jwtTokenProvider());
    }
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf((csrf)->csrf.disable());

		// 토큰을 사용하는 경우 인가를 적용한 URI 설정
		http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
				.requestMatchers("/file/**").hasRole("ADMIN")
				.requestMatchers("/board/**").hasAnyRole("USER", "ADMIN")
				.requestMatchers("/**", "/css/**", "/js/**", "/images/**").permitAll()
				.requestMatchers("/member/insert", "/member/login").permitAll());

		// Session 기반의 인증기반을 사용하지 않고 추후 JWT를 이용하여서 인증 예정
		http.sessionManagement((session) -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		// Spring Security JWT 필터 로드
		http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider()), 
				UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
	
	@Bean
	@ConditionalOnMissingBean(UserDetailsService.class)
	InMemoryUserDetailsManager userDetailsService() {
		return new InMemoryUserDetailsManager(
				User.withUsername("foo").password("{noop}demo").roles("ADMIN").build(),
				User.withUsername("bar").password("{noop}demo").roles("USER").build(),
				User.withUsername("ted").password("{noop}demo").roles("USER","ADMIN").build());
	}


}