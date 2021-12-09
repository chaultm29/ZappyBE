package com.example.springboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.springboot.services.MyUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JWTRequestFilter jwtRequestFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.userDetailsService(myUserDetailsService)
			.passwordEncoder(passwordEncoder());
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		return super.userDetailsService();
	}
	protected void configure(HttpSecurity http) throws Exception{
		http.cors().and().csrf().disable().authorizeRequests().antMatchers("/login", "/register", "/ping", "/forgotPassword").permitAll()
		.anyRequest().authenticated().and()
		.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
	}
}
