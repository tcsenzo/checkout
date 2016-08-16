package com.senzo.qettal.checkout.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.senzo.qettal.checkout.users.Users;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	private Users users;
	@Autowired
	private CustomAuthenticationSuccessHandler successHandler;
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		UserDetailsService service = (email) -> users.findByEmail(email)
				.map(u -> new User(u.getEmail(), u.getPassword(), Collections.emptyList()))
				.orElseThrow(() -> new UsernameNotFoundException("Couldn't find user with email "+email));
		
		auth.userDetailsService(service)
			.passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.exceptionHandling()
				.authenticationEntryPoint(restAuthenticationEntryPoint)
		
				.and().authorizeRequests()
						.antMatchers(HttpMethod.POST, "/users").permitAll()
						.antMatchers(HttpMethod.POST, "/payments/moip/callback").permitAll()
						.anyRequest().authenticated()
				
				.and().formLogin()
						.successHandler(successHandler)
						.failureHandler(new SimpleUrlAuthenticationFailureHandler())
						.usernameParameter("email")
						.passwordParameter("password");		
	}
	

}
