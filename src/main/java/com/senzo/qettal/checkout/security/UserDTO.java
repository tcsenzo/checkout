package com.senzo.qettal.checkout.security;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonInclude(Include.NON_NULL)
public class UserDTO {
	@JsonProperty("auth_id")
	private String authId;
	@JsonProperty
	private String name;
	@JsonProperty
	private String email;
	
	public String getEmail() {
		return email;
	}
	
	public String getAuthId() {
		return authId;
	}

	public String getName() {
		return name;
	}
}