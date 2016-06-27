package com.senzo.qettal.checkout.users;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonInclude(Include.NON_NULL)
public class UserDTO {

	@JsonProperty
	private String name;
	@JsonProperty
	private String email;
	@JsonProperty
	private String password;

	/**
	 * @deprecated Jackson eyes only
	 */
	UserDTO() {
	}
	
	public User toModel(PasswordEncoder encoder) {
		return new User(name, email, encoder.encode(password));
	}

}