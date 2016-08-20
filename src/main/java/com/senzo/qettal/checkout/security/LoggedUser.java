package com.senzo.qettal.checkout.security;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.senzo.qettal.checkout.users.User;

@Component
@Scope(scopeName="request", proxyMode=TARGET_CLASS)
public class LoggedUser {

	private User cachedUser;
	
	public Optional<User> getUser() {
		return Optional.ofNullable(cachedUser);
	}


	public void setUser(User cachedUser) {
		this.cachedUser = cachedUser;
	}

}
