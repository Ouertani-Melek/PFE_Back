package com.backend.guestnhouse.payload.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtResponse {
	private String token;
	@JsonIgnore
	private String type = "Bearer";
	@JsonIgnore
	private String id;
	@JsonIgnore
	private String username;
	@JsonIgnore
	private String email;
	@JsonIgnore
	private List<String> roles;
	
	

	public JwtResponse(String accessToken) {
		this.token = accessToken;
	}

	public JwtResponse(String accessToken, String id, String username, String email, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}
}
