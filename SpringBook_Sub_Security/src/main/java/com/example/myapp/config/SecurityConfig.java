package com.example.myapp.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.formLogin()
				.loginPage("/member/login")
				.usernameParameter("userid")
				.defaultSuccessUrl("/")
			.and()
			.logout()
				.logoutUrl("/member/logout")
				.logoutSuccessUrl("/member/login")
				.invalidateHttpSession(true);
		http.authorizeHttpRequests()
			.requestMatchers("/file/**").hasRole("ADMIN")
			.requestMatchers("/board/**").hasAnyRole("USER", "ADMIN")
			.requestMatchers("/**").permitAll()
			.requestMatchers("/css/**").permitAll()
			.requestMatchers("/js/**").permitAll()
			.requestMatchers("/images/**").permitAll()
			.requestMatchers("/member/insert").permitAll()
			.requestMatchers("/member/login").permitAll();
		return http.build();
	}
	
	@Bean
	@ConditionalOnMissingBean(UserDetailsService.class)
	public InMemoryUserDetailsManager userDetailsService() {
		return new InMemoryUserDetailsManager(
				User
					.withUsername("foo")
					.password("{noop}demo")
					.roles("ADMIN").build(),
				User
					.withUsername("bar")
					.password("{noop}demo")
					.roles("USER").build(),
				User
					.withUsername("ted")
					.password("{noop}demo")
					.roles("USER","ADMIN").build());
	}
}